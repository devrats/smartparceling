/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 19-Jul-21
 *   Time: 3:50 PM
 *   File: UserController.java
 */

package com.example.smartparceling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/dashboard/{path}")
    public String dashboard(@PathVariable("path") int path, Model model, Principal principal) {
        model.addAttribute("principal",principal);
        model.addAttribute("title", "Dashboard");
        model.addAttribute("value", path);
        return "Dashboard";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("title","Profile");
        return "Profile";
    }

    @RequestMapping("/receiveOrder")
    public String receiveOrder(Model model) {
        model.addAttribute("title","Receive Orders");
        return "ReceiveOrder";
    }

    @RequestMapping("/requestOrder")
    public String requestOrder(Model model) {
        model.addAttribute("title","Request Orders");
        return "RequestOrder";
    }

    @RequestMapping("/editProfile")
    public String editProfile(Model model) {
        model.addAttribute("title","Edit Profile");
        return "Edit";
    }

    @RequestMapping("/previousOrder")
    public String previousOrder(Model model) {
        model.addAttribute("title","Previous Orders");
        return "PreviousOrder";
    }

    @RequestMapping("/recharge")
    public String recharge(Model model) {
        model.addAttribute("title","Recharge");
        return "Recharge";
    }

}