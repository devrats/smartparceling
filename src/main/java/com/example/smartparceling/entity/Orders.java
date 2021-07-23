/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 9:15 AM
 *   File: Orders.java
 */

package com.example.smartparceling.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class Orders {

    @Id
    private int id;
    private String thing;
    @OneToOne(mappedBy = "orderFrom",cascade = CascadeType.ALL)
    private Address from;
    @OneToOne(mappedBy = "orderTo", cascade = CascadeType.ALL)
    private Address to;
    private float weight;
    private String via;
    private Date date;
    @OneToOne(mappedBy = "order")
    private OrderCompleted orderCompleted;
    @OneToOne(mappedBy = "order")
    private OrderOnTheWay orderOnTheWay;
    @OneToOne(mappedBy = "order")
    private OrderPending orderPending;
    @OneToOne(mappedBy = "order")
    private OrderReceived orderReceived;
    @OneToOne(mappedBy = "order")
    private OrderRequested orderRequested;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", thing='" + thing + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                ", via='" + via + '\'' +
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
        return getId() == orders.getId() && Float.compare(orders.getWeight(), getWeight()) == 0 && Objects.equals(getThing(), orders.getThing()) && Objects.equals(getFrom(), orders.getFrom()) && Objects.equals(getTo(), orders.getTo()) && Objects.equals(getVia(), orders.getVia()) && Objects.equals(getDate(), orders.getDate()) && Objects.equals(getOrderCompleted(), orders.getOrderCompleted()) && Objects.equals(getOrderOnTheWay(), orders.getOrderOnTheWay()) && Objects.equals(getOrderPending(), orders.getOrderPending()) && Objects.equals(getOrderReceived(), orders.getOrderReceived()) && Objects.equals(getOrderRequested(), orders.getOrderRequested());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getThing(), getFrom(), getTo(), getWeight(), getVia(), getDate(), getOrderCompleted(), getOrderOnTheWay(), getOrderPending(), getOrderReceived(), getOrderRequested());
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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
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

    public OrderReceived getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(OrderReceived orderReceived) {
        this.orderReceived = orderReceived;
    }

    public OrderRequested getOrderRequested() {
        return orderRequested;
    }

    public void setOrderRequested(OrderRequested orderRequested) {
        this.orderRequested = orderRequested;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, String via, Date date, OrderRequested orderRequested) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
        this.orderRequested = orderRequested;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, String via, Date date, OrderReceived orderReceived) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
        this.orderReceived = orderReceived;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, String via, Date date, OrderPending orderPending) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
        this.orderPending = orderPending;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, String via, Date date, OrderOnTheWay orderOnTheWay) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
        this.orderOnTheWay = orderOnTheWay;
    }

    public Orders(int id, String thing, Address from, Address to, float weight, String via, Date date, OrderCompleted orderCompleted) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
        this.orderCompleted = orderCompleted;
    }

    public Orders() {
    }

    public Orders(int id, String thing, Address from, Address to, float weight, String via, Date date, OrderCompleted orderCompleted, OrderOnTheWay orderOnTheWay, OrderPending orderPending, OrderReceived orderReceived, OrderRequested orderRequested) {
        this.id = id;
        this.thing = thing;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
        this.orderCompleted = orderCompleted;
        this.orderOnTheWay = orderOnTheWay;
        this.orderPending = orderPending;
        this.orderReceived = orderReceived;
        this.orderRequested = orderRequested;
    }
}