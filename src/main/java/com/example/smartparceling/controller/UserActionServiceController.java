/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 29-Jul-21
 *   Time: 8:50 AM
 *   File: UserActionServiceController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.database.*;
import com.example.smartparceling.email.Email;
import com.example.smartparceling.email.EmailService;
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


@Controller
@RequestMapping("/user")
public class UserActionServiceController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private VisitRepository visitRepository;
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
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EmailService emailService;


    @RequestMapping("/acceptOrder/{id}")
    public String acceptOrder(@PathVariable("id") int id, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        if (person.getAdhaarVerified()) {
            boolean isOwner = false;
            OrderReceived orderReceived = orderReceivedRepository.findOrderReceivedById(id);
            List<OrderReceived> orderReceived1 = person.getOrderReceived();
            for (OrderReceived orderReceived2 : orderReceived1) {
                if (orderReceived.getPerson().getId() == orderReceived2.getPerson().getId()) {
                    isOwner = true;
                    break;
                }
            }
            if (isOwner) {
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
                OrderRequested orderRequested = orderRequestedRepository.
                        findOrderRequestedByOrder(orderReceived.getOrder());
                orderRequestedRepository.delete(orderRequested);
                List<OrderRequested> orderRequested1 = user.getOrderRequested();
                orderRequested1.remove(orderRequested);
                orderReceived1.remove(orderReceived);
                person.setOrderReceived(orderReceived1);
                user.setOrderRequested(orderRequested1);
                List<Message> message = user.getMessage();
                Message message1 = new Message();
                message1.setMessage("Your order has been accepted and details have been mailed!");
                message1.setPerson(user);
                message1.setId(2);
                message.add(message1);
                message1.setPerson(user);
                user.setMessage(message);
                Email email = new Email();
                emailService.sendEmail(user.getEmail(), email.getHead(),
                        email.getMsg(person.getName(), person.getEmail(), person.getPhone()), false);
                emailService.sendEmail(person.getEmail(), email.getHead(),
                        email.getMsg(user.getName(), user.getEmail(), user.getPhone()), false);
                List<OrderReceived> orderReceived3 = orderReceivedRepository.findOrderReceivedsByOrder(orderRequested.getOrder());
                for (OrderReceived received : orderReceived3) {
                    orderReceivedRepository.delete(received);
                }
                orderPendingRepository.save(orderPending);
                orderOnTheWayRepository.save(orderOnTheWay);
                messageRepository.save(message1);
                personRepository.save(person);
                personRepository.save(user);
                return "redirect:/user/dashboard/1";
            } else {
                return "redirect:/user/dashboard/1";
            }

        } else {
            List<Message> message = person.getMessage();
            Message message1 = new Message();
            message1.setMessage("Your photo identity proof is not verified yet...");
            message1.setPerson(person);
            message.add(message1);
            person.setMessage(message);
            messageRepository.save(message1);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/report/{id}")
    public String report(@PathVariable("id") int id, Model model, Principal principal) {
        Person person = personRepository.findPersonByUserName(principal.getName());
        Orders ordersById = orderRepository.findOrdersById(id);
        OrderCompleted orderCompleted = orderCompletedRepository.findOrderCompletedByOrder(ordersById);
        if (orderCompleted.getPerson().getId() == person.getId()) {
            Person user = orderCompleted.getUser();
            try {
                String path = new ClassPathResource("").getFile().getAbsolutePath() + "\\static\\img\\proof.png";
                byte[] file1 = user.getAdhaar();
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(file1);
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Email email = new Email();
            emailService.sendEmail(person.getEmail(), email.getHead3(), email.getMsg3(), true);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/endVisit/{id}")
    public String endVisit(@PathVariable("id") int id, Model model, Principal principal) {
        boolean isOwner = false;
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<Visit> visits = person.getVisits();
        for (Visit visit : visits) {
            if (person.getId() == visit.getPerson().getId()) {
                isOwner = true;
            }
        }
        if (isOwner) {
            Visit visit = visitRepository.findVisitById(id);
            List<OrderReceived> orderReceived = person.getOrderReceived();
            List<OrderReceived> orderReceived1 = new ArrayList<>();
            System.out.println(orderReceived.size());
            for (OrderReceived received : orderReceived) {
                if (received.getOrder().getFrom().getCity().equals(visit.getFrom().getCity()) &&
                        received.getOrder().getFrom().getState().equals(visit.getFrom().getState()) &&
                        received.getOrder().getTo().getCity().equals(visit.getTo().getCity()) &&
                        received.getOrder().getTo().getState().equals(visit.getTo().getState()) &&
                        received.getOrder().getDate().equals(visit.getDate())) {
                    System.out.println("idhar aya");
                    orderReceivedRepository.delete(received);
                    orderReceived1.add(received);
                }
            }
            orderReceived.removeAll(orderReceived1);
            person.setOrderReceived(orderReceived);
            visits.remove(visit);
            personRepository.save(person);
            visitRepository.delete(visit);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/completeOnTheWayOrder/{id}")
    public String completeOnTheWayOrder(@PathVariable("id") int id, Model model, Principal principal) {
        boolean isOwner = false;
        OrderOnTheWay orderOnTheWay = orderOnTheWayRepository.findOrderOnTheWayById(id);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderOnTheWay> orderOnTheWays = person.getOrderOnTheWay();
        for (OrderOnTheWay orderOnTheWay1 : orderOnTheWays) {
            if (orderOnTheWay.getPerson().getId() == orderOnTheWay1.getPerson().getId()) {
                isOwner = true;
                break;
            }
        }
        if (isOwner) {
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
            person.setAccountBalance(person.getAccountBalance() - orderOnTheWay.getOrder().getCharge());
            user.setAccountBalance(user.getAccountBalance() + orderOnTheWay.getOrder().getCharge());
            orderCompletedRepository.save(orderCompleted);
            orderCompletedByUserRepository.save(orderCompletedByUser);
            OrderPending orderPending = orderPendingRepository.
                    findOrderPendingByOrder(orderOnTheWay.getOrder());
            orderPendingRepository.delete(orderPending);
            orderOnTheWayRepository.delete(orderOnTheWay);
            List<OrderPending> orderPendings = user.getOrderPending();
            orderPendings.remove(orderPending);
            orderOnTheWays.remove(orderOnTheWay);
            person.setOrderOnTheWay(orderOnTheWays);
            user.setOrderPending(orderPendings);
            List<Message> message = user.getMessage();
            Message message1 = new Message();
            message1.setMessage("Congo you have completed an order");
            message1.setPerson(user);
            message.add(message1);
            user.setMessage(message);
            messageRepository.save(message1);
            personRepository.save(person);
            personRepository.save(user);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/completePendingOrder/{id}")
    public String completePendingOrder(@PathVariable("id") int id, Model model, Principal principal) {
        boolean isOwner = false;
        OrderPending orderPending = orderPendingRepository.findOrderPendingById(id);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderPending> orderPendingList = person.getOrderPending();
        for (OrderPending orderPending1 : orderPendingList) {
            if (orderPending.getPerson().getId() == orderPending1.getPerson().getId()) {
                isOwner = true;
                break;
            }
        }
        if (isOwner) {
            Person user = personRepository.findPersonById(orderPending.getOwner().getId());
            OrderCompletedByUser orderCompletedByUser = new OrderCompletedByUser();
            OrderCompleted orderCompleted = new OrderCompleted();
            orderCompletedByUser.setOrder(orderPending.getOrder());
            orderCompletedByUser.setPerson(orderPending.getPerson());
            orderCompletedByUser.setOwner(orderPending.getOwner());
            orderCompleted.setOrder(orderPending.getOrder());
            orderCompleted.setPerson(orderPending.getOwner());
            orderCompleted.setUser(orderPending.getPerson());
            List<OrderCompleted> orderCompleted1 = user.getOrderCompleted();
            List<OrderCompletedByUser> orderCompletedByUser1 = person.getOrderCompletedByUser();
            orderCompletedByUser1.add(orderCompletedByUser);
            orderCompleted1.add(orderCompleted);
            person.setAccountBalance(person.getAccountBalance() + orderPending.getOrder().getCharge());
            user.setAccountBalance(user.getAccountBalance() - orderPending.getOrder().getCharge());
            orderCompletedRepository.save(orderCompleted);
            orderCompletedByUserRepository.save(orderCompletedByUser);
            OrderOnTheWay orderOnTheWay = orderOnTheWayRepository.
                    findOrderOnTheWayByOrder(orderPending.getOrder());
            List<OrderOnTheWay> orderOnTheWays = user.getOrderOnTheWay();
            orderOnTheWays.remove(orderOnTheWay);
            orderPendingList.remove(orderPending);
            person.setOrderPending(orderPendingList);
            user.setOrderOnTheWay(orderOnTheWays);
            List<Message> message = user.getMessage();
            Message message1 = new Message();
            message1.setMessage("Your order has been completed");
            message1.setPerson(user);
            message1.setOrderId(orderPending.getOrder().getId());
            message.add(message1);
            user.setMessage(message);
            messageRepository.save(message1);
            personRepository.save(person);
            personRepository.save(user);
            orderOnTheWayRepository.delete(orderOnTheWay);
            orderPendingRepository.delete(orderPending);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/cancelReceivedOrder/{id}")
    public String cancelReceivedOrder(@PathVariable("id") int id, Model model, Principal principal) {
        boolean isOwner = false;
        OrderReceived orderReceived = orderReceivedRepository.findOrderReceivedById(id);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderReceived> orderReceivedList = person.getOrderReceived();
        for (OrderReceived orderReceived1 : orderReceivedList) {
            if (orderReceived.getPerson().getId() == orderReceived1.getPerson().getId()) {
                isOwner = true;
                break;
            }
        }
        if (isOwner) {
            orderReceivedList.remove(orderReceived);
            person.setOrderReceived(orderReceivedList);
            orderReceivedRepository.delete(orderReceived);
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/cancelRequestedOrder/{id}")
    public String cancelRequestedOrder(@PathVariable("id") int id, Model model, Principal principal) {
        boolean isOwner = false;
        OrderRequested orderRequested = orderRequestedRepository.findOrderRequestedById(id);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderRequested> orderRequestedList = person.getOrderRequested();
        for (OrderRequested orderRequested1 : orderRequestedList) {
            if (orderRequested.getPerson().getId() == orderRequested1.getPerson().getId()) {
                isOwner = true;
                break;
            }
        }
        if (isOwner) {
            orderRequestedList.remove(orderRequested);
            person.setOrderRequested(orderRequestedList);
            orderRequestedRepository.delete(orderRequested);
            List<OrderReceived> orderReceived = orderReceivedRepository.findOrderReceivedsByOrder(orderRequested.getOrder());
            for (OrderReceived received : orderReceived) {
                orderReceivedRepository.delete(received);
            }
            orderRepository.delete(orderRequested.getOrder());
            personRepository.save(person);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/cancelOnTheWayOrder/{id}")
    public String cancelOnTheWayOrder(@PathVariable("id") int id, Model model, Principal principal) {
        boolean isOwner = false;
        OrderOnTheWay orderOnTheWay = orderOnTheWayRepository.findOrderOnTheWayById(id);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderOnTheWay> orderOnTheWays = person.getOrderOnTheWay();
        for (OrderOnTheWay orderOnTheWay1 : orderOnTheWays) {
            if (orderOnTheWay.getPerson().getId() == orderOnTheWay1.getPerson().getId()) {
                isOwner = true;
                break;
            }
        }
        if (isOwner) {
            OrderPending orderPending = orderPendingRepository.findOrderPendingByOrder(orderOnTheWay.getOrder());
            Person user = personRepository.findPersonById(orderOnTheWay.getUser().getId());
            List<OrderPending> orderPendingList = user.getOrderPending();
            orderPendingList.remove(orderPending);
            user.setOrderPending(orderPendingList);
            orderOnTheWays.remove(orderOnTheWay);
            person.setOrderOnTheWay(orderOnTheWays);
            OrderRequested orderRequested = new OrderRequested();
            orderRequested.setPerson(person);
            orderRequested.setOrder(orderOnTheWay.getOrder());
            List<OrderRequested> orderRequestedList = person.getOrderRequested();
            orderRequestedList.add(orderRequested);
            List<Message> message = user.getMessage();
            Message message1 = new Message();
            message1.setMessage("One of your order has been cancelled by owner");
            message1.setPerson(user);
            message.add(message1);
            user.setMessage(message);
            messageRepository.save(message1);
            person.setOrderRequested(orderRequestedList);
            orderRepository.save(orderRequested.getOrder());
            orderRequestedRepository.save(orderRequested);
            orderPendingRepository.delete(orderPending);
            orderOnTheWayRepository.delete(orderOnTheWay);
            personRepository.save(person);
            personRepository.save(user);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }

    @RequestMapping("/cancelPendingOrder/{id}")
    public String cancelPendingOrder(@PathVariable("id") int id, Model model, Principal principal) {
        boolean isOwner = false;
        OrderPending orderPending = orderPendingRepository.findOrderPendingById(id);
        Person person = personRepository.findPersonByUserName(principal.getName());
        List<OrderPending> orderPendings = person.getOrderPending();
        for (OrderPending orderPending2 : orderPendings) {
            if (orderPending.getPerson().getId() == orderPending2.getPerson().getId()) {
                isOwner = true;
                break;
            }
        }
        if (isOwner) {
            OrderOnTheWay orderOnTheWay = orderOnTheWayRepository.findOrderOnTheWayByOrder(orderPending.getOrder());
            Person user = personRepository.findPersonById(orderPending.getOwner().getId());
            System.out.println(orderPendings.remove(orderPending));
            person.setOrderPending(orderPendings);
            OrderRequested orderRequested = new OrderRequested();
            orderRequested.setPerson(user);
            orderRequested.setOrder(orderPending.getOrder());
            List<OrderRequested> orderRequestedList = user.getOrderRequested();
            orderRequestedList.add(orderRequested);
            user.setOrderRequested(orderRequestedList);
            List<OrderOnTheWay> orderOnTheWays = user.getOrderOnTheWay();
            orderOnTheWays.remove(orderOnTheWay);
            user.setOrderOnTheWay(orderOnTheWays);
            List<Message> message = user.getMessage();
            Message message1 = new Message();
            message1.setMessage("Your order on the way has been cancelled by user your details are" +
                    "there soon someone will accept it");
            message1.setPerson(user);
            message.add(message1);
            user.setMessage(message);
            messageRepository.save(message1);
            orderRepository.save(orderRequested.getOrder());
            orderRequestedRepository.save(orderRequested);
            orderPendingRepository.delete(orderPending);
            orderOnTheWayRepository.delete(orderOnTheWay);
            personRepository.save(person);
            personRepository.save(user);
            return "redirect:/user/dashboard/1";
        } else {
            return "redirect:/user/dashboard/1";
        }
    }
}