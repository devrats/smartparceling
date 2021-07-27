/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 12:51 PM
 *   File: Address.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String houseNumber;
    private String street;
    private String state;
    private String city;
    private String zip;
    @OneToOne()
    private Person person;
    @OneToOne()
    private Orders orderFrom;
    @OneToOne()
    private Orders orderTo;
    @OneToOne()
    private Visit visitFrom;
    @OneToOne()
    private Visit visitTo;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", houseNumber='" + houseNumber + '\'' +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", person=" + person +
                ", orderFrom=" + orderFrom +
                ", orderTo=" + orderTo +
                ", visitFrom=" + visitFrom +
                ", visitTo=" + visitTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getId() == address.getId() && Objects.equals(getHouseNumber(), address.getHouseNumber()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getState(), address.getState()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getZip(), address.getZip()) && Objects.equals(getPerson(), address.getPerson()) && Objects.equals(getOrderFrom(), address.getOrderFrom()) && Objects.equals(getOrderTo(), address.getOrderTo()) && Objects.equals(getVisitFrom(), address.getVisitFrom()) && Objects.equals(getVisitTo(), address.getVisitTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHouseNumber(), getStreet(), getState(), getCity(), getZip(), getPerson(), getOrderFrom(), getOrderTo(), getVisitFrom(), getVisitTo());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Orders getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Orders orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Orders getOrderTo() {
        return orderTo;
    }

    public void setOrderTo(Orders orderTo) {
        this.orderTo = orderTo;
    }

    public Visit getVisitFrom() {
        return visitFrom;
    }

    public void setVisitFrom(Visit visitFrom) {
        this.visitFrom = visitFrom;
    }

    public Visit getVisitTo() {
        return visitTo;
    }

    public void setVisitTo(Visit visitTo) {
        this.visitTo = visitTo;
    }

    public Address(String houseNumber, String street, String state, String city, String zip, Visit visitFrom, Visit visitTo) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.visitFrom = visitFrom;
        this.visitTo = visitTo;
    }

    public Address(String houseNumber, String street, String state, String city, String zip, Orders orderFrom, Orders orderTo) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.orderFrom = orderFrom;
        this.orderTo = orderTo;
    }

    public Address(String houseNumber, String street, String state, String city, String zip, Person person) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.person = person;
    }

    public Address() {
    }

    public Address(int id, String houseNumber, String street, String state, String city, String zip, Person person) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.person = person;
    }

    public Address(int id, String houseNumber, String street, String state, String city, String zip, Person person, Orders orderFrom, Orders orderTo, Visit visitFrom, Visit visitTo) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.person = person;
        this.orderFrom = orderFrom;
        this.orderTo = orderTo;
        this.visitFrom = visitFrom;
        this.visitTo = visitTo;
    }

}