/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 30-Jul-21
 *   Time: 3:18 PM
 *   File: Verification.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.PersonRepository;
import com.example.smartparceling.email.Email;
import com.example.smartparceling.email.EmailService;
import com.example.smartparceling.email.Mobile;
import com.example.smartparceling.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class Verification {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Mobile mobile;

    Random rnd = new Random();
    String otp = null;

    @RequestMapping("/email")
    public String email(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("name", person.getName());
        model.addAttribute("email", person.getEmail());
        model.addAttribute("title", "email verification");
        model.addAttribute("wrongOtp", false);
        model.addAttribute("change", false);
        model.addAttribute("nonchange", true);
        int number = rnd.nextInt(999999);
        otp = String.format("%06d", number);
        Email email = new Email();
        emailService.sendEmail(person.getEmail(), email.getHead2(), email.getMsg1(otp), false);
        return "EmailVerification";
    }

    @RequestMapping("/emailVerification")
    public String emailVerification(Model model, Principal principal, @RequestParam("otp") String pass) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        if (pass.equals(otp)) {
            person.setEmailVerified(true);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        } else {
            model.addAttribute("name", person.getName());
            model.addAttribute("email", person.getEmail());
            model.addAttribute("title", "email verification");
            model.addAttribute("change", false);
            model.addAttribute("wrongOtp", true);
            model.addAttribute("nonchange", true);
            return "EmailVerification";
        }
    }

    @RequestMapping("/changeEmailDisplay")
    public String changeEmailDisplay(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("wrongOtp", false);
        model.addAttribute("name", person.getName());
        model.addAttribute("email", person.getEmail());
        model.addAttribute("title", "email verification");
        model.addAttribute("change", true);
        model.addAttribute("nonchange", false);
        return "EmailVerification";
    }

    @RequestMapping("/changeEmail")
    public String changeEmail(Model model, Principal principal, @RequestParam("newEmail") String newEmail) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        person.setEmail(newEmail);
        personRepository.save(person);
        return "redirect:/user/email";
    }

    @RequestMapping("/phone")
    public String phone(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("name", person.getName());
        model.addAttribute("phone", person.getPhone());
        model.addAttribute("title", "phone verification");
        model.addAttribute("wrongOtp", false);
        model.addAttribute("change", false);
        model.addAttribute("nonchange", true);
        int number = rnd.nextInt(999999);
        otp = String.format("%06d", number);
        System.out.println(mobile.sendSms(person.getPhone(), otp));
        return "PhoneVerification";
    }

    @RequestMapping("/phoneVerification")
    public String phoneVerification(Model model, Principal principal, @RequestParam("otp") String pass) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        if (pass.equals(otp)) {
            person.setPhoneVerified(true);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        } else {
            model.addAttribute("name", person.getName());
            model.addAttribute("phone", person.getPhone());
            model.addAttribute("title", "phone verification");
            model.addAttribute("change", false);
            model.addAttribute("wrongOtp", true);
            model.addAttribute("nonchange", true);
            return "PhoneVerification";
        }
    }

    @RequestMapping("/changePhoneDisplay")
    public String changePhoneDisplay(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("wrongOtp", false);
        model.addAttribute("name", person.getName());
        model.addAttribute("phone", person.getPhone());
        model.addAttribute("title", "phone verification");
        model.addAttribute("change", true);
        model.addAttribute("nonchange", false);
        return "PhoneVerification";
    }

    @RequestMapping("/changePhone")
    public String changePhone(Model model, Principal principal, @RequestParam("newPhone") String newPhone) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        person.setPhone(newPhone);
        personRepository.save(person);
        return "redirect:/user/phone";
    }
}