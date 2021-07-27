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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Smart Parceling");
        return "Home";
    }
    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("person",new Person());
        model.addAttribute("address",new Address());
        model.addAttribute("zip_true",false);
        model.addAttribute("userAvailable",false);
        model.addAttribute("pass",false);
        model.addAttribute("title", "Sign Up");
        return "Login";
    }

    @GetMapping("/signin")
    public String login(Model model){
        model.addAttribute("title","Login");
        return "SignIn";
    }
}