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
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/dashboard/{path}")
    public String dashboard(@PathVariable("path") int path, Model model) {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("value", path);
        return "Dashboard";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("title","Profile");
        return "Profile";
    }

}