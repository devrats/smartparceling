/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 18-Jul-21
 *   Time: 9:25 AM
 *   File: HomeController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.PersonRepository;
import com.example.smartparceling.entity.Address;
import com.example.smartparceling.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Smart Parceling");
        return "Home";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("person",new Person());
        model.addAttribute("address",new Address());
        model.addAttribute("title", "Sign Up");
        return "Login";
    }

    @GetMapping("/signin")
    public String login(Model model){
        model.addAttribute("title","Login");
        return "SignIn";
    }

    @PostMapping("/loginsuccess")
    public String loginSuccess(Model model, @RequestParam(name = "confirm_password")
            String password, @ModelAttribute @Valid Person person, BindingResult result) throws Exception {
        person.getAddress().setPerson(person);
        model.addAttribute("passwords",password);
        model.addAttribute("pass",false);
        model.addAttribute("userAvailable",false);
        Person personList = personRepository.findPersonByUserName(person.getUserName());
        if(!(personList ==null)){
            model.addAttribute("userAvailable",true);
            model.addAttribute("person",person);
            model.addAttribute("address",person.getAddress());
            model.addAttribute("title","Sign Up");
            return "Login";
        }
        if(!person.getPassword().equals(password)){
            model.addAttribute("pass",true);
            model.addAttribute("person",person);
            model.addAttribute("address",person.getAddress());
            model.addAttribute("title","Sign Up");
            return "Login";
        }
        if(result.hasErrors()){
            model.addAttribute("person",person);
            model.addAttribute("address",person.getAddress());
            model.addAttribute("title","Sign Up");
            return "Login";
        } else {
            person.setRole("ROLE_USER");
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
            personRepository.save(person);
            model.addAttribute("title","Login");
            return "redirect:/user/dashboard/1";
        }

    }

    @RequestMapping("/signin-error")
    public String signInError(Model model){
        model.addAttribute("title","Login");
        model.addAttribute("signin-error",true);
        return "SignIn";
    }

}