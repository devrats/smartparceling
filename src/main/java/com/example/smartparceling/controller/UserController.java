/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 19-Jul-21
 *   Time: 3:50 PM
 *   File: UserController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.PersonRepository;
import com.example.smartparceling.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/dashboard/{path}")
    public String dashboard(@PathVariable("path") int path, Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("person",person);
        model.addAttribute("title", "Dashboard");
        model.addAttribute("value", path);
        return "Dashboard";
    }

    @RequestMapping("/profile")
    public String profile(Model model,Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("person",person);
        model.addAttribute("address",person.getAddress());
        model.addAttribute("title","Profile");
        return "Profile";
    }

    @RequestMapping("/receiveOrder")
    public String receiveOrder(Model model) {
        model.addAttribute("from",new Address());
        model.addAttribute("to",new Address());
        model.addAttribute("visit",new Visit());
        model.addAttribute("zip_true1",false);
        model.addAttribute("zip_true2",false);
        model.addAttribute("weight",false);
        model.addAttribute("title","Receive Orders");
        return "ReceiveOrder";
    }

    @RequestMapping("/requestOrder")
    public String requestOrder(Model model) {
        model.addAttribute("from",new Address());
        model.addAttribute("to",new Address());
        model.addAttribute("order",new Orders());
        model.addAttribute("zip_true1",false);
        model.addAttribute("zip_true2",false);
        model.addAttribute("date",false);
        model.addAttribute("weight",false);
        model.addAttribute("title","Request Orders");
        return "RequestOrder";
    }

    @RequestMapping("/editProfile")
    public String editProfile(Model model,Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("zip_true",false);
        model.addAttribute("person",person);
        model.addAttribute("address",person.getAddress());
        model.addAttribute("title","Edit Profile");
        return "Edit";
    }

    @RequestMapping("/recharge")
    public String recharge(Model model,Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("person",person);
        model.addAttribute("title","Recharge");
        return "Recharge";
    }

    @RequestMapping("/previousOrder")
    public String previousOrder(Model model,Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("person",person);
        model.addAttribute("title","Previous Orders");
        return "PreviousOrder";
    }

}