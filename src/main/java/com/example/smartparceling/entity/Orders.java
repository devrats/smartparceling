/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 9:15 AM
 *   File: Orders.java
 */

package com.example.smartparceling.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "This Field cannot be blank")
    private String thing;
    @OneToOne(mappedBy = "orderFrom",cascade = CascadeType.ALL)
    private Address from;
    @OneToOne(mappedBy = "orderTo",cascade = CascadeType.ALL)
    private Address to;
    private float weight;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @OneToOne(mappedBy = "order")
    private OrderCompleted orderCompleted;
    @OneToOne(mappedBy = "order")
    private OrderOnTheWay orderOnTheWay;
    @OneToOne(mappedBy = "order")
    private OrderPending orderPending;
    @OneToMany(mappedBy = "order")
    private List<OrderReceived> orderReceived;
    @OneToOne(mappedBy = "order")
    private OrderRequested orderRequested;
    private int charge;

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", thing='" + thing + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                ", date=" + date +
                ", orderCompleted=" + orderCompleted +
                ", orderOnTheWay=" + orderOnTheWay +
                ", orderPending=" + orderPending +
                ", orderReceived=" + orderReceived +
                ", orderRequested=" + orderRequested +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return getId() == orders.getId() && Float.compare(orders.getWeight(), getWeight()) == 0 && Objects.equals(getThing(), orders.getThing()) && Objects.equals(getFrom(), orders.getFrom()) && Objects.equals(getTo(), orders.getTo()) && Objects.equals(getDate(), orders.getDate()) && Objects.equals(getOrderCompleted(), orders.getOrderCompleted()) && Objects.equals(getOrderOnTheWay(), orders.getOrderOnTheWay()) && Objects.equals(getOrderPending(), orders.getOrderPending()) && Objects.equals(getOrderReceived(), orders.getOrderReceived()) && Objects.equals(getOrderRequested(), orders.getOrderRequested());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getThing(), getFrom(), getTo(), getWeight(), getDate(), getOrderCompleted(), getOrderOnTheWay(), getOrderPending(), getOrderReceived(), getOrderRequested());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public Address getTo() {
        return to;
    }

    public void setTo(Address to) {
        this.to = to;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderCompleted getOrderCompleted() {
        return orderCompleted;
    }

    public void setOrderCompleted(OrderCompleted orderCompleted) {
        this.orderCompleted = orderCompleted;
    }

    public OrderOnTheWay getOrderOnTheWay() {
        return orderOnTheWay;
    }

    public void setOrderOnTheWay(OrderOnTheWay orderOnTheWay) {
        this.orderOnTheWay = orderOnTheWay;
    }

    public OrderPending getOrderPending() {
        return orderPending;
    }

    public void setOrderPending(OrderPending orderPending) {
        this.orderPending = orderPending;
    }

    public List<OrderReceived> getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(List<OrderReceived> orderReceived) {
        this.orderReceived = orderReceived;
    }

    public OrderRequested getOrderRequested() {
        return orderRequested;
    }

    public void setOrderRequested(OrderRequested orderRequested) {
        this.orderRequested = orderRequested;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, Date date, OrderRequested orderRequested) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.date = date;
        this.orderRequested = orderRequested;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, Date date, List<OrderReceived> orderReceived) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.date = date;
        this.orderReceived = orderReceived;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, Date date, OrderPending orderPending) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.date = date;
        this.orderPending = orderPending;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, Date date, OrderOnTheWay orderOnTheWay) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.date = date;
        this.orderOnTheWay = orderOnTheWay;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, Date date, OrderCompleted orderCompleted) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.date = date;
        this.orderCompleted = orderCompleted;
    }

    public Orders() {
    }

    public Orders(int id, String thing, Address from, Address to, float weight, Date date, OrderCompleted orderCompleted, OrderOnTheWay orderOnTheWay, OrderPending orderPending, List<OrderReceived> orderReceived, OrderRequested orderRequested) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.date = date;
        this.orderCompleted = orderCompleted;
        this.orderOnTheWay = orderOnTheWay;
        this.orderPending = orderPending;
        this.orderReceived = orderReceived;
        this.orderRequested = orderRequested;
    }
}