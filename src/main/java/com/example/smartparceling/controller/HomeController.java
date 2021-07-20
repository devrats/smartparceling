/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 18-Jul-21
 *   Time: 9:25 AM
 *   File: HomeController.java
 */

package com.example.smartparceling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Smart Parceling");
        return "Home";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("title", "Sign Up");
        return "Login";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("title","Login");
        return "Home";
    }

}