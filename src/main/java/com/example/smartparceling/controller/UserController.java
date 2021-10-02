/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 19-Jul-21
 *   Time: 3:50 PM
 *   File: UserController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.MessageRepository;
import com.example.smartparceling.database.OrderCompletedRepository;
import com.example.smartparceling.database.OrderRequestedRepository;
import com.example.smartparceling.database.PersonRepository;
import com.example.smartparceling.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private OrderCompletedRepository orderCompletedRepository;
    @Autowired
    private OrderRequestedRepository orderRequestedRepository;

    @RequestMapping("/dashboard/{path}")
    public String dashboard(@PathVariable("path") int path, Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        if (person.isEmailVerified() && person.isPhoneVerified()) {
            List<Message> message = person.getMessage();
            if (!message.isEmpty()) {
                for (Message message1 : message) {
                    messageRepository.delete(message1);
                }
                model.addAttribute("msg", message);
                List<Message> messages = new ArrayList<>();
                person.setMessage(messages);
                model.addAttribute("title", "msg");
                return "Message";
            }
            int request = orderRequestedRepository.countOrderRequestedByPerson(person);
            int complete = orderCompletedRepository.countOrderCompletedByPerson(person);
            model.addAttribute("request", request);
            model.addAttribute("complete", complete);
            model.addAttribute("person", person);
            model.addAttribute("title", "Dashboard");
            model.addAttribute("value", path);
            List<String> names = new ArrayList<>();
            for (OrderOnTheWay orderOnTheWay : person.getOrderOnTheWay()) {
                names.add(orderOnTheWay.getUser().getUserName());
            }
            for (OrderPending orderPending : person.getOrderPending()) {
                names.add(orderPending.getOwner().getUserName());
            }
            model.addAttribute("names",names);
            return "Dashboard";
        } else if (!person.isEmailVerified()) {
            return "redirect:/user/email";
        } else {
            return "redirect:/user/phone";
        }
    }

    @RequestMapping("/profile")
    public String profile(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        try {
            String path = new ClassPathResource("").getFile().getAbsolutePath() + "\\static\\img\\profile.png";
            byte[] file1 = person.getImage();
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(file1);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("person", person);
        model.addAttribute("address", person.getAddress());
        model.addAttribute("title", "Profile");
        return "Profile";
    }

    @RequestMapping("/receiveOrder")
    public String receiveOrder(Model model) {
        model.addAttribute("from", new Address());
        model.addAttribute("to", new Address());
        model.addAttribute("visit", new Visit());
        model.addAttribute("zip_true1", false);
        model.addAttribute("zip_true2", false);
        model.addAttribute("weight", false);
        model.addAttribute("title", "Receive Orders");
        return "ReceiveOrder";
    }

    @RequestMapping("/requestOrder")
    public String requestOrder(Model model) {
        model.addAttribute("from", new Address());
        model.addAttribute("to", new Address());
        model.addAttribute("order", new Orders());
        model.addAttribute("zip_true1", false);
        model.addAttribute("zip_true2", false);
        model.addAttribute("date", false);
        model.addAttribute("weight", false);
        model.addAttribute("addressValid", false);
        model.addAttribute("notEnoughBalance", false);
        model.addAttribute("fees", 0);
        model.addAttribute("title", "Request Orders");
        return "RequestOrder";
    }

    @RequestMapping("/editProfile")
    public String editProfile(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("zip_true", false);
        model.addAttribute("person", person);
        model.addAttribute("address", person.getAddress());
        model.addAttribute("title", "Edit Profile");
        return "Edit";
    }

    @RequestMapping("/recharge")
    public String recharge(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("person", person);
        model.addAttribute("title", "Recharge");
        return "Recharge";
    }

    @RequestMapping("/previousOrder")
    public String previousOrder(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("person", person);
        model.addAttribute("title", "Previous Orders");
        return "PreviousOrder";
    }

    @RequestMapping("/creditAccount")
    public String creditAccount(Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("person", person);
        model.addAttribute("amount", false);
        model.addAttribute("amountupi", false);
        model.addAttribute("amt", false);
        model.addAttribute("amtupi", false);
        model.addAttribute("creditAccount", new CreditAccount());
        model.addAttribute("title", "Credit Account");
        model.addAttribute("upi", false);
        return "CreditAccount";
    }
}