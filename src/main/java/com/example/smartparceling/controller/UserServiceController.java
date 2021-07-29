/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 25-Jul-21
 *   Time: 11:08 AM
 *   File: UserServiceController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.*;
import com.example.smartparceling.entity.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserServiceController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRequestedRepository orderRequestedRepository;
    @Autowired
    private OrderReceivedRepository orderReceivedRepository;
    @Autowired
    private OrderPendingRepository orderPendingRepository;
    @Autowired
    private OrderOnTheWayRepository orderOnTheWayRepository;
    @Autowired
    private OrderCompletedByUserRepository orderCompletedByUserRepository;
    @Autowired
    private OrderCompletedRepository orderCompletedRepository;


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
            model.addAttribute("title", "Edit Profile");
            return "Edit";
        } else if (address.getHouseNumber() == "" || address.getState().equals("") || address.getCity().equals("") ||
                address.getZip().length() != 6 || address.getState().equals("Choose...")) {
            model.addAttribute("zip_true", true);
            model.addAttribute("person", person);
            model.addAttribute("address", address);
            model.addAttribute("title", "Edit Profile");
            return "Edit";
        } else {
            personRepository.save(personByUserName);
            return "redirect:/user/profile";
        }
    }

    @RequestMapping("/requestOrderNow")
    public String requestOrderNow(Model model, Principal principal,
                                  @ModelAttribute @Valid OrderRequested orderRequested, BindingResult result) {
        Address from = orderRequested.getOrder().getFrom();
        Address to = orderRequested.getOrder().getTo();
        Orders order = orderRequested.getOrder();
        orderRequested.getOrder().setId(9);
        orderRequested.getOrder().getFrom().setOrderFrom(orderRequested.getOrder());
        orderRequested.getOrder().getTo().setOrderTo(orderRequested.getOrder());
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderRequested> orderRequestedList1 = person.getOrderRequested();
        orderRequestedList1.add(orderRequested);
        orderRequested.setPerson(person);
        model.addAttribute("zip_true1", false);
        model.addAttribute("zip_true2", false);
        if (order.getThing() == "" || order.getWeight() == 0.0) {
            model.addAttribute("order", order);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("weight", true);
            model.addAttribute("title", "Request Orders");
            return "RequestOrder";
        } else if (order.getDate() == null) {
            model.addAttribute("order", order);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("date", true);
            model.addAttribute("title", "Request Orders");
            return "RequestOrder";
        } else if (from.getHouseNumber() == "" || from.getStreet().equals("") || from.getCity().equals("") ||
                from.getZip().length() != 6 || from.getState().equals("Choose...")) {
            model.addAttribute("zip_true1", true);
            model.addAttribute("order", order);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Request Orders");
            return "RequestOrder";
        } else if (to.getHouseNumber() == "" || to.getState().equals("") || to.getCity().equals("") ||
                to.getZip().length() != 6 || to.getState().equals("Choose...")) {
            model.addAttribute("zip_true2", true);
            model.addAttribute("order", order);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Request Orders");
            return "RequestOrder";
        } else {
            orderRequestedRepository.save(orderRequested);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/receiveOrderNow")
    public String receiveOrderNow(Model model, Principal principal,
                                  @ModelAttribute @Valid Visit visit, BindingResult result) {
        Address from = visit.getFrom();
        Address to = visit.getTo();
        visit.getFrom().setVisitFrom(visit);
        visit.getTo().setVisitTo(visit);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<Visit> personVisits = person.getVisits();
        personVisits.add(visit);
        visit.setPerson(person);
        model.addAttribute("zip_true1", false);
        model.addAttribute("zip_true2", false);
        model.addAttribute("via", false);
        if (visit.getWeight() == 0.0) {
            model.addAttribute("visit", visit);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("weight", true);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else if (visit.getVia().equals("Choose...")) {
            model.addAttribute("visit", visit);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else if (result.hasErrors()) {
            model.addAttribute("visit", visit);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else if (from.getHouseNumber() == "" || from.getStreet().equals("") || from.getCity().equals("") ||
                from.getZip().length() != 6 || from.getState().equals("Choose...")) {
            model.addAttribute("zip_true1", true);
            model.addAttribute("visit", visit);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else if (to.getHouseNumber() == "" || to.getState().equals("") || to.getCity().equals("") ||
                to.getZip().length() != 6 || to.getState().equals("Choose...")) {
            model.addAttribute("zip_true2", true);
            model.addAttribute("visit", visit);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("title", "Receive Orders");
            return "ReceiveOrder";
        } else {
            visitRepository.save(visit);
            personRepository.save(person);
            List<Integer> orderRequestedListInt = orderRequestedRepository.findOrder(visit.getFrom().getCity(),
                    visit.getFrom().getState(),visit.getTo().getCity(),visit.getTo().getState(),
                    visit.getWeight(),visit.getDate());
            List<OrderReceived> orderReceivedList = person.getOrderReceived();
            for (Integer orderRequestedInt : orderRequestedListInt) {
                OrderRequested orderRequested = orderRequestedRepository.
                        findOrderRequestedById(orderRequestedInt);
                OrderReceived orderReceived = new OrderReceived();
                orderReceived.setOrder(orderRequested.getOrder());
                orderReceived.setOwner(orderRequested.getPerson());
                orderReceived.setPerson(person);
                orderReceivedList.add(orderReceived);
                orderReceivedRepository.save(orderReceived);
            }
            person.setOrderReceived(orderReceivedList);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/pay")
    @ResponseBody
    public String pay(@RequestBody Map<String, Object> data, Principal principal) throws RazorpayException {
        var client = new RazorpayClient("rzp_test_4sgOUR0hFBHwtn", "buf7y58LORq7JKsFuExL7xXS");
        JSONObject object = new JSONObject();
        int amount = Integer.parseInt(data.get("amount").toString());
        object.put("amount", amount * 100);
        object.put("currency", "INR");
        object.put("receipt", "txn_1234");
        Order order = client.Orders.create(object);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<Payment> payment1 = person.getPayment();
        Payment payment = new Payment(order.get("id"), amount, order.get("receipt"), "created", (Date) order.get("created_at"));
        payment1.add(payment);
        payment.setPerson(person);
        paymentRepository.save(payment);
        personRepository.save(person);
        return order.toString();
    }

    @RequestMapping("/paySuccess")
    @ResponseBody
    public String paySuccess(@RequestBody Map<String, Object> data, Principal principal) {
        Payment payment = paymentRepository.findPaymentByPaymentId((String) data.get("razorpay_order_id"));
        payment.setStatus((String) data.get("status"));
        payment.setTransactionId((String) data.get("razorpay_payment_id"));
        paymentRepository.save(payment);
        Person person = personRepository.findPersonByUserName(principal.getName());
        float amount = payment.getAmount();
        person.setAccountBalance(person.getAccountBalance() + (int) amount);
        personRepository.save(person);
        return payment.toString();
    }

    @RequestMapping("/acceptOrder/{id}")
    public String acceptOrder(@PathVariable("id") int id, Model model,Principal principal){
        boolean isOwner = false;
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderReceived> orderReceived1 = person.getOrderReceived();
        for (OrderReceived orderReceived : orderReceived1) {
            if(person.getId() == orderReceived.getPerson().getId()){
                isOwner = true;
                break;
            }
        }
        if(isOwner){
            OrderReceived orderReceived = orderReceivedRepository.findOrderReceivedById(id);
            Person user = personRepository.findPersonById(orderReceived.getOwner().getId());
            OrderPending orderPending = new OrderPending();
            OrderOnTheWay orderOnTheWay = new OrderOnTheWay();
            orderPending.setOrder(orderReceived.getOrder());
            orderPending.setPerson(orderReceived.getPerson());
            orderPending.setOwner(orderReceived.getOwner());
            orderOnTheWay.setOrder(orderReceived.getOrder());
            orderOnTheWay.setPerson(orderReceived.getOwner());
            orderOnTheWay.setUser(orderReceived.getPerson());
            List<OrderPending> orderPending1 = person.getOrderPending();
            List<OrderOnTheWay> orderOnTheWay1 = user.getOrderOnTheWay();
            orderOnTheWay1.add(orderOnTheWay);
            orderPending1.add(orderPending);
            orderPendingRepository.save(orderPending);
            orderOnTheWayRepository.save(orderOnTheWay);
            OrderRequested orderRequested = orderRequestedRepository.
                    findOrderRequestedByOrder(orderReceived.getOrder());
            List<OrderRequested> orderRequested1 = user.getOrderRequested();
            orderRequested1.remove(orderRequested);
            orderReceived1.remove(orderReceived);
            person.setOrderReceived(orderReceived1);
            user.setOrderRequested(orderRequested1);
            personRepository.save(person);
            personRepository.save(user);
            orderRequestedRepository.delete(orderRequested);
            orderReceivedRepository.delete(orderReceived);
            return "redirect:/user/dashboard/1";
        }
        else {
            return "redirect:/user/dashboard/1";
        }
    }
    
    @RequestMapping("/completeOnTheWayOrder/{id}")
    public String completeOnTheWayOrder(@PathVariable("id") int id, Model model,Principal principal){
        boolean isOwner = false;
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderOnTheWay> orderOnTheWays = person.getOrderOnTheWay();
        for (OrderOnTheWay orderOnTheWay : orderOnTheWays) {
            if(person.getId() == orderOnTheWay.getPerson().getId()){
                isOwner = true;
                break;
            }
        }
        if(isOwner){
            OrderOnTheWay orderOnTheWay = orderOnTheWayRepository.findOrderOnTheWayById(id);
            Person user = personRepository.findPersonById(orderOnTheWay.getUser().getId());
            OrderCompleted orderCompleted = new OrderCompleted();
            OrderCompletedByUser orderCompletedByUser = new OrderCompletedByUser();
            orderCompleted.setOrder(orderOnTheWay.getOrder());
            orderCompleted.setPerson(orderOnTheWay.getPerson());
            orderCompleted.setUser(orderOnTheWay.getUser());
            orderCompletedByUser.setOrder(orderOnTheWay.getOrder());
            orderCompletedByUser.setPerson(orderOnTheWay.getUser());
            orderCompletedByUser.setOwner(orderOnTheWay.getPerson());
            List<OrderCompleted> orderCompleted1 = person.getOrderCompleted();
            List<OrderCompletedByUser> orderCompletedByUser1 = user.getOrderCompletedByUser();
            orderCompletedByUser1.add(orderCompletedByUser);
            orderCompleted1.add(orderCompleted);
            orderCompletedRepository.save(orderCompleted);
            orderCompletedByUserRepository.save(orderCompletedByUser);
            OrderPending orderPending = orderPendingRepository.
                    findOrderPendingByOrder(orderOnTheWay.getOrder());
            List<OrderPending> orderPendings = user.getOrderPending();
            orderPendings.remove(orderPending);
            orderOnTheWays.remove(orderOnTheWay);
            person.setOrderOnTheWay(orderOnTheWays);
            user.setOrderPending(orderPendings);
            personRepository.save(person);
            personRepository.save(user);
            orderPendingRepository.delete(orderPending);
            orderOnTheWayRepository.delete(orderOnTheWay);
            return "redirect:/user/dashboard/1";
        }
        else {
            return "redirect:/user/dashboard/1";
        }
    }
}