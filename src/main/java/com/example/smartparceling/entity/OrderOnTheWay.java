/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 7:55 AM
 *   File: OrderOnTheWay.java
 */

package com.example.smartparceling.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderOnTheWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Person user;
    @OneToOne()
    private Orders order;

    @Override
    public String toString() {
        return "OrderOnTheWay{" +
                "id=" + id +
                ", person=" + person +
                ", user=" + user +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderOnTheWay)) return false;
        OrderOnTheWay that = (OrderOnTheWay) o;
        return getId() == that.getId() && Objects.equals(getPerson(), that.getPerson()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getOrder(), that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getUser(), getOrder());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderOnTheWay(Person person, Person user, Orders order) {
        this.person = person;
        this.user = user;
        this.order = order;
    }

    public OrderOnTheWay() {
    }

    public OrderOnTheWay(int id, Person person, Person user, Orders order) {
        this.id = id;
        this.person = person;
        this.user = user;
        this.order = order;
    }
}