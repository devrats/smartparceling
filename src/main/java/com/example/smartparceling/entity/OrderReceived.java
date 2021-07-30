/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 7:55 AM
 *   File: OrderReceived.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Person owner;
    @ManyToOne(cascade = CascadeType.ALL)
    private Orders order;

    @Override
    public String toString() {
        return "OrderReceived{" +
                "id=" + id +
                ", person=" + person +
                ", owner=" + owner +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderReceived)) return false;
        OrderReceived that = (OrderReceived) o;
        return getId() == that.getId() && Objects.equals(getPerson(), that.getPerson()) && Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getOrder(), that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getOwner(), getOrder());
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderReceived(Person person, Person owner, Orders order) {
        this.person = person;
        this.owner = owner;
        this.order = order;
    }

    public OrderReceived() {
    }

    public OrderReceived(int id, Person person, Person owner, Orders order) {
        this.id = id;
        this.person = person;
        this.owner = owner;
        this.order = order;
    }
}