/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 25-Jul-21
 *   Time: 11:08 AM
 *   File: UserServiceController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.charge.Charge;
import com.example.smartparceling.charge.Distance;
import com.example.smartparceling.charge.GeoCodeService;
import com.example.smartparceling.database.*;
import com.example.smartparceling.entity.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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
    private OrderRepository orderRepository;
    @Autowired
    private CreditAccountRepository creditAccountRepository;


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
                                  @ModelAttribute @Valid OrderRequested orderRequested, BindingResult result)
            throws IOException, InterruptedException {
        Address from = orderRequested.getOrder().getFrom();
        Address to = orderRequested.getOrder().getTo();
        Orders order = orderRequested.getOrder();
        model.addAttribute("zip_true1", false);
        model.addAttribute("zip_true2", false);
        model.addAttribute("weight", false);
        model.addAttribute("date", false);
        model.addAttribute("addressValid", false);
        model.addAttribute("notEnoughBalance", false);
        model.addAttribute("order", order);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("title", "Request Orders");
        model.addAttribute("fees",0);
        List calculateFrom = GeoCodeService.calculate
                (order.getFrom().getCity() +  ", India");
        List calculateTo = GeoCodeService.calculate
                (order.getTo().getCity() + ", India");
        if(calculateFrom.isEmpty() || calculateTo.isEmpty()){
            model.addAttribute("addressValid",true);
            return "RequestOrder";
        }
        float distance = Distance.distFrom(Float.parseFloat((String) calculateFrom.get(0)),Float.parseFloat((String) calculateFrom.get(1))
        ,Float.parseFloat((String) calculateTo.get(0)),Float.parseFloat((String) calculateTo.get(1)));
        int charge = new Charge().charge(distance);
        orderRequested.getOrder().getFrom().setOrderFrom(orderRequested.getOrder());
        orderRequested.getOrder().getTo().setOrderTo(orderRequested.getOrder());
        orderRequested.getOrder().setCharge(charge);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderRequested> orderRequestedList1 = person.getOrderRequested();
        orderRequestedList1.add(orderRequested);
        orderRequested.setPerson(person);
        if (order.getThing() == "" || order.getWeight() == 0.0) {
            model.addAttribute("weight", true);
            return "RequestOrder";
        } else if (order.getDate() == null) {
            model.addAttribute("date", true);
            return "RequestOrder";
        } else if (from.getHouseNumber() == "" || from.getStreet().equals("") || from.getCity().equals("") ||
                from.getZip().length() != 6 || from.getState().equals("Choose...")) {
            model.addAttribute("zip_true1", true);
            return "RequestOrder";
        } else if (to.getHouseNumber() == "" || to.getState().equals("") || to.getCity().equals("") ||
                to.getZip().length() != 6 || to.getState().equals("Choose...")) {
            model.addAttribute("zip_true2", true);
            return "RequestOrder";
        }else if(charge>=person.getAccountBalance()){
            model.addAttribute("notEnoughBalance", true);
            model.addAttribute("fees",charge);
            return "RequestOrder";
        }else if (charge==0){
            model.addAttribute("addressValid",true);
            return "RequestOrder";
        }
        else {
            orderRepository.save(orderRequested.getOrder());
            orderRequestedRepository.save(orderRequested);
            List<Integer> visitByPerson = visitRepository.findVisitByPerson(order.getFrom().getCity(), order.getFrom().getState(),
                    order.getTo().getCity(), order.getTo().getState(), order.getWeight(), order.getDate());
            for (Integer byPerson : visitByPerson) {
                Person personById = personRepository.findPersonById(byPerson);
                List<OrderReceived> orderReceived = personById.getOrderReceived();
                OrderReceived orderReceived1 = new OrderReceived();
                orderReceived1.setOrder(order);
                orderReceived1.setPerson(personById);
                orderReceived1.setOwner(person);
                orderReceived.add(orderReceived1);
                orderReceivedRepository.save(orderReceived1);
                personRepository.save(personById);
            }
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
                    visit.getFrom().getState(), visit.getTo().getCity(), visit.getTo().getState(),
                    visit.getWeight(), visit.getDate());
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

    @RequestMapping("creditAccountNow")
    public String creditAccountNow(Model model, Principal principal, @ModelAttribute @Valid
            CreditAccount creditAccount, BindingResult result) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("creditAccount", creditAccount);
        model.addAttribute("title", "Credit Account");
        model.addAttribute("upi", false);
        model.addAttribute("amount",false);
        model.addAttribute("amountupi",false);
        model.addAttribute("amt",false);
        model.addAttribute("amtupi",false);
        model.addAttribute("person",person);
        if (result.hasErrors()) {
            return "CreditAccount";
        }if(creditAccount.getAmount()==0){
            model.addAttribute("amount",true);
            return "CreditAccount";
        } else if((person.getAccountBalance()-creditAccount.getAmount())<=10){
            model.addAttribute("amt",true);
            return "CreditAccount";
        } else {
            List<CreditAccount> creditAccounts = person.getCreditAccounts();
            creditAccount.setPerson(person);
            creditAccount.setUpi("");
            creditAccount.setStatus("pending");
            creditAccounts.add(creditAccount);
            creditAccountRepository.save(creditAccount);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("creditAccountNowWithUpi")
    public String creditAccountNowWithUpi(@RequestParam("amount") int amt, Model model, Principal principal, @RequestParam("upi") String upi) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        model.addAttribute("creditAccount", new CreditAccount());
        model.addAttribute("title", "Credit Account");
        model.addAttribute("upi", false);
        model.addAttribute("person",person);
        model.addAttribute("amount",false);
        model.addAttribute("amountupi",false);
        model.addAttribute("amt",false);
        model.addAttribute("amtupi",false);
        if(amt==0){
            model.addAttribute("amountupi",true);
            return "CreditAccount";
        }else if((person.getAccountBalance()-amt)<=10){
            model.addAttribute("amtupi",true);
            return "CreditAccount";
        }else if (upi.isEmpty() || upi.isBlank()) {
            model.addAttribute("upi", true);
            return "CreditAccount";
        } else {
            CreditAccount creditAccount = new CreditAccount("0", "0", "0");
            List<CreditAccount> creditAccounts = person.getCreditAccounts();
            creditAccount.setPerson(person);
            creditAccount.setUpi(upi);
            creditAccount.setStatus("pending");
            creditAccount.setAmount(amt);
            creditAccounts.add(creditAccount);
            creditAccountRepository.save(creditAccount);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }

    }

    @RequestMapping("profileUpdate")
    public String profileUpdate(@RequestParam("file") MultipartFile file,Principal principal) throws IOException {
        Person person = personRepository.findPersonByUserName(principal.getName());
        byte[] bytes = file.getBytes();
        person.setImage(bytes);
        personRepository.save(person);
        return "redirect:/user/profile";
    }

    @RequestMapping("/paySuccess")
    @ResponseBody
    public ResponseEntity<?> paySuccess(@RequestBody Map<String, Object> data, Principal principal) {
        Payment payment = paymentRepository.findPaymentByPaymentId((String) data.get("razorpay_order_id"));
        payment.setStatus((String) data.get("status"));
        payment.setTransactionId((String) data.get("razorpay_payment_id"));
        paymentRepository.save(payment);
        Person person = personRepository.findPersonByUserName(principal.getName());
        float amount = payment.getAmount();
        person.setAccountBalance(person.getAccountBalance() + (int) amount);
        personRepository.save(person);
        return ResponseEntity.ok(Map.of("msg","updated"));
    }

}
