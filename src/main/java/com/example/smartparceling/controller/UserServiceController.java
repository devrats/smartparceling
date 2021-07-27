/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 25-Jul-21
 *   Time: 11:08 AM
 *   File: UserServiceController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.OrderRequestedRepository;
import com.example.smartparceling.database.PersonRepository;
import com.example.smartparceling.database.VisitRepository;
import com.example.smartparceling.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserServiceController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OrderRequestedRepository orderRequestedRepository;
    @Autowired
    private VisitRepository visitRepository;

    @PostMapping("/update")
    public String update(Model model, Principal principal, @ModelAttribute Person person, BindingResult result)
            throws Exception {
        Address address = person.getAddress();
        Person personByUserName = personRepository.findPersonByUserName(principal.getName());
        personByUserName.setName(person.getName());
        personByUserName.setEmail(person.getEmail());
        personByUserName.setPhone(person.getPhone());
        personByUserName.getAddress().setHouseNumber(address.getHouseNumber());
        personByUserName.getAddress().setStreet(address.getStreet());
        personByUserName.getAddress().setState(address.getState());
        personByUserName.getAddress().setCity(address.getCity());
        personByUserName.getAddress().setZip(address.getZip());
        personByUserName.getAddress().setPerson(personByUserName);
        if (person.getName().equals("") || person.getEmail().equals("") || person.getPhone().equals("")) {
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Sign Up");
            return "Edit";
        } else if (address.getHouseNumber() == "" || address.getState().equals("") || address.getCity().equals("") ||
                address.getZip().length() != 6 || address.getState().equals("Choose...")) {
            model.addAttribute("zip_true",true);
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Sign Up");
            return "Edit";
        } else {
            personRepository.save(personByUserName);
            return "redirect:/user/profile";
        }
    }

    @RequestMapping("/requestOrderNow")
    public String requestOrderNow(Model model, Principal principal,
                                  @ModelAttribute @Valid OrderRequested orderRequested, BindingResult result){
        Address from = orderRequested.getOrder().getFrom();
        Address to = orderRequested.getOrder().getTo();
        Orders order = orderRequested.getOrder();
        orderRequested.getOrder().setId(6);
        orderRequested.getOrder().getFrom().setOrderFrom(orderRequested.getOrder());
        orderRequested.getOrder().getTo().setOrderTo(orderRequested.getOrder());
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderRequested> orderRequestedList1 = person.getOrderRequested();
        orderRequestedList1.add(orderRequested);
        orderRequested.setPerson(person);
        model.addAttribute("zip_true1",false);
        model.addAttribute("zip_true2",false);
        if(order.getThing()=="" || order.getWeight()==0.0){
            model.addAttribute("order", order);
            model.addAttribute("from", from);
            model.addAttribute("to",to);
            model.addAttribute("weight",true);
            model.addAttribute("title", "Sign Up");
            return "RequestOrder";
        } else if(order.getDate()==null){
            model.addAttribute("order", order);
            model.addAttribute("from", from);
            model.addAttribute("to",to);
            model.addAttribute("date",true);
            model.addAttribute("title", "Sign Up");
            return "RequestOrder";
        }else if (from.getHouseNumber() == "" || from.getStreet().equals("") || from.getCity().equals("") ||
                from.getZip().length() != 6 || from.getState().equals("Choose...")) {
            model.addAttribute("zip_true1",true);
            model.addAttribute("order", order);
            model.addAttribute("from", from);
            model.addAttribute("to",to);
            model.addAttribute("title", "Sign Up");
            return "RequestOrder";
        } else if (to.getHouseNumber() == "" || to.getState().equals("") || to.getCity().equals("") ||
                to.getZip().length() != 6 || to.getState().equals("Choose...")) {
            model.addAttribute("zip_true2",true);
            model.addAttribute("order", order);
            model.addAttribute("from",from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Sign Up");
            return "RequestOrder";
        } else {
            orderRequestedRepository.save(orderRequested);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/receiveOrderNow")
    public String receiveOrderNow(Model model, Principal principal,
                                  @ModelAttribute @Valid Visit visit,BindingResult result){
        Address from = visit.getFrom();
        Address to = visit.getTo();
        visit.getFrom().setVisitFrom(visit);
        visit.getTo().setVisitTo(visit);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<Visit> personVisits = person.getVisits();
        personVisits.add(visit);
        visit.setPerson(person);
        model.addAttribute("zip_true1",false);
        model.addAttribute("zip_true2",false);
        model.addAttribute("via",false);
        if(visit.getWeight()==0.0){
            model.addAttribute("visit",visit);
            model.addAttribute("from", from);
            model.addAttribute("to",to);
            model.addAttribute("weight",true);
            model.addAttribute("title", "Sign Up");
            return "ReceiveOrder";
        }else if(visit.getVia().equals("Choose...")){
            model.addAttribute("visit",visit);
            model.addAttribute("from", from);
            model.addAttribute("to",to);
            model.addAttribute("title", "Sign Up");
            return "ReceiveOrder";
        }else if (result.hasErrors()) {
            model.addAttribute("visit",visit);
            model.addAttribute("from",from);
            model.addAttribute("to",to);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else if (from.getHouseNumber() == "" || from.getStreet().equals("") || from.getCity().equals("") ||
                from.getZip().length() != 6 || from.getState().equals("Choose...")) {
            model.addAttribute("zip_true1",true);
            model.addAttribute("visit",visit);
            model.addAttribute("from", from);
            model.addAttribute("to",to);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else if (to.getHouseNumber() == "" || to.getState().equals("") || to.getCity().equals("") ||
                to.getZip().length() != 6 || to.getState().equals("Choose...")) {
            model.addAttribute("zip_true2",true);
            model.addAttribute("visit",visit);
            model.addAttribute("from",from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else {
            visitRepository.save(visit);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }
    }
}