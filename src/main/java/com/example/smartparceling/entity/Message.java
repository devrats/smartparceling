/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 29-Jul-21
 *   Time: 7:50 PM
 *   File: Message.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    @ManyToOne
    private Person person;
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return getId() == message1.getId() && Objects.equals(getMessage(), message1.getMessage()) && Objects.equals(getPerson(), message1.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMessage(), getPerson());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Message() {
    }

    public Message(String message, Person person) {
        this.message = message;
        this.person = person;
    }

    public Message(int id, String message, Person person) {
        this.id = id;
        this.message = message;
        this.person = person;
    }
}