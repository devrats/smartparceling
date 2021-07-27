/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 6:44 PM
 *   File: OrderRequested.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderRequested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Person person;
    @OneToOne(cascade = CascadeType.ALL)
    private Orders order;

    @Override
    public String toString() {
        return "OrderRequested{" +
                "id=" + id +
                ", person=" + person +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderRequested)) return false;
        OrderRequested that = (OrderRequested) o;
        return getId() == that.getId() && Objects.equals(getPerson(), that.getPerson()) && Objects.equals(getOrder(), that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getOrder());
    }

    public OrderRequested(Person person, Orders order) {
        this.person = person;
        this.order = order;
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

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderRequested() {
    }

    public OrderRequested(int id, Person person, Orders order) {
        this.id = id;
        this.person = person;
        this.order = order;
    }
}