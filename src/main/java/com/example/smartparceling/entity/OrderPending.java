/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 6:45 PM
 *   File: OrderPending.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;

@Entity
public class OrderPending {

    @Id
    private int id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Person owner;
    @OneToOne(cascade = CascadeType.ALL)
    private Orders order;

    @Override
    public String toString() {
        return "OrderPending{" +
                "id=" + id +
                ", person=" + person +
                ", owner=" + owner +
                ", order=" + order +
                '}';
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

    public OrderPending(Person person, Person owner, Orders order) {
        this.person = person;
        this.owner = owner;
        this.order = order;
    }

    public OrderPending() {
    }

    public OrderPending(int id, Person person, Person owner, Orders order) {
        this.id = id;
        this.person = person;
        this.owner = owner;
        this.order = order;
    }
}