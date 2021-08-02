/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 12:49 PM
 *   File: Visit.java
 */

package com.example.smartparceling.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Person person;
    @OneToOne(mappedBy = "visitFrom", cascade = CascadeType.ALL)
    private Address from;
    @OneToOne(mappedBy = "visitTo", cascade = CascadeType.ALL)
    private Address to;
    private float weight;
    private String via;
    @NotNull(message = "This field cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", person=" + person +
                ", from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                ", via='" + via + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;
        Visit visit = (Visit) o;
        return getId() == visit.getId() && Float.compare(visit.getWeight(), getWeight()) == 0 && Objects.equals(getPerson(), visit.getPerson()) && Objects.equals(getFrom(), visit.getFrom()) && Objects.equals(getTo(), visit.getTo()) && Objects.equals(getVia(), visit.getVia()) && Objects.equals(getDate(), visit.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getFrom(), getTo(), getWeight(), getVia(), getDate());
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

    public Visit(Person person, Address from, Address to, float weight, String via, Date date) {
        this.person = person;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
    }

    public Visit() {
    }

    public Visit(int id, Person person, Address from, Address to, float weight, String via, Date date) {
        this.id = id;
        this.person = person;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.via = via;
        this.date = date;
    }
}