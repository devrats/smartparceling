/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 12:48 PM
 *   File: Person.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long phone;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    private String email;
    private byte[] image;
    private int accountBalance;
    private String role;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(mappedBy = "person")
    private List<OrderRequested> orderRequested;
    @OneToMany(mappedBy = "person")
    private List<OrderCompletedByUser> orderCompletedByUser;
    @OneToMany(mappedBy = "person")
    private List<OrderCompleted> orderCompleted;
    @OneToMany(mappedBy = "person")
    private List<OrderPending> orderPending;
    @OneToMany(mappedBy = "person")
    private List<OrderReceived> orderReceived;
    @OneToMany(mappedBy = "person")
    private List<OrderOnTheWay> orderOnTheWay;
    @OneToMany(mappedBy = "owner")
    private List<OrderCompletedByUser> orderCompletedByUserFor;
    @OneToMany(mappedBy = "user")
    private List<OrderCompleted> orderCompletedBy;
    @OneToMany(mappedBy = "owner")
    private List<OrderPending> orderPendingFrom;
    @OneToMany(mappedBy = "owner")
    private List<OrderReceived> orderReceivedFrom;
    @OneToMany(mappedBy = "user")
    private List<OrderOnTheWay> orderOnTheWayBy;
    @OneToMany(mappedBy = "person")
    private List<Visit> visits;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", image=" + Arrays.toString(image) +
                ", accountBalance=" + accountBalance +
                ", role='" + role + '\'' +
                ", address=" + address +
                ", orderRequested=" + orderRequested +
                ", orderCompletedByUser=" + orderCompletedByUser +
                ", orderCompleted=" + orderCompleted +
                ", orderPending=" + orderPending +
                ", orderReceived=" + orderReceived +
                ", orderOnTheWay=" + orderOnTheWay +
                ", orderCompletedByUserFor=" + orderCompletedByUserFor +
                ", orderCompletedBy=" + orderCompletedBy +
                ", orderPendingFrom=" + orderPendingFrom +
                ", orderReceivedFrom=" + orderReceivedFrom +
                ", orderOnTheWayBy=" + orderOnTheWayBy +
                ", visits=" + visits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && getPhone() == person.getPhone() && getAccountBalance() == person.getAccountBalance() && Objects.equals(getName(), person.getName()) && Objects.equals(getUserName(), person.getUserName()) && Objects.equals(getPassword(), person.getPassword()) && Objects.equals(getEmail(), person.getEmail()) && Arrays.equals(getImage(), person.getImage()) && Objects.equals(getRole(), person.getRole()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getOrderRequested(), person.getOrderRequested()) && Objects.equals(getOrderCompletedByUser(), person.getOrderCompletedByUser()) && Objects.equals(getOrderCompleted(), person.getOrderCompleted()) && Objects.equals(getOrderPending(), person.getOrderPending()) && Objects.equals(getOrderReceived(), person.getOrderReceived()) && Objects.equals(getOrderOnTheWay(), person.getOrderOnTheWay()) && Objects.equals(getOrderCompletedByUserFor(), person.getOrderCompletedByUserFor()) && Objects.equals(getOrderCompletedBy(), person.getOrderCompletedBy()) && Objects.equals(getOrderPendingFrom(), person.getOrderPendingFrom()) && Objects.equals(getOrderReceivedFrom(), person.getOrderReceivedFrom()) && Objects.equals(getOrderOnTheWayBy(), person.getOrderOnTheWayBy()) && Objects.equals(getVisits(), person.getVisits());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getPhone(), getUserName(), getPassword(), getEmail(), getAccountBalance(), getRole(), getAddress(), getOrderRequested(), getOrderCompletedByUser(), getOrderCompleted(), getOrderPending(), getOrderReceived(), getOrderOnTheWay(), getOrderCompletedByUserFor(), getOrderCompletedBy(), getOrderPendingFrom(), getOrderReceivedFrom(), getOrderOnTheWayBy(), getVisits());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderRequested> getOrderRequested() {
        return orderRequested;
    }

    public void setOrderRequested(List<OrderRequested> orderRequested) {
        this.orderRequested = orderRequested;
    }

    public List<OrderCompletedByUser> getOrderCompletedByUser() {
        return orderCompletedByUser;
    }

    public void setOrderCompletedByUser(List<OrderCompletedByUser> orderCompletedByUser) {
        this.orderCompletedByUser = orderCompletedByUser;
    }

    public List<OrderCompleted> getOrderCompleted() {
        return orderCompleted;
    }

    public void setOrderCompleted(List<OrderCompleted> orderCompleted) {
        this.orderCompleted = orderCompleted;
    }

    public List<OrderPending> getOrderPending() {
        return orderPending;
    }

    public void setOrderPending(List<OrderPending> orderPending) {
        this.orderPending = orderPending;
    }

    public List<OrderReceived> getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(List<OrderReceived> orderReceived) {
        this.orderReceived = orderReceived;
    }

    public List<OrderOnTheWay> getOrderOnTheWay() {
        return orderOnTheWay;
    }

    public void setOrderOnTheWay(List<OrderOnTheWay> orderOnTheWay) {
        this.orderOnTheWay = orderOnTheWay;
    }

    public List<OrderCompletedByUser> getOrderCompletedByUserFor() {
        return orderCompletedByUserFor;
    }

    public void setOrderCompletedByUserFor(List<OrderCompletedByUser> orderCompletedByUserFor) {
        this.orderCompletedByUserFor = orderCompletedByUserFor;
    }

    public List<OrderCompleted> getOrderCompletedBy() {
        return orderCompletedBy;
    }

    public void setOrderCompletedBy(List<OrderCompleted> orderCompletedBy) {
        this.orderCompletedBy = orderCompletedBy;
    }

    public List<OrderPending> getOrderPendingFrom() {
        return orderPendingFrom;
    }

    public void setOrderPendingFrom(List<OrderPending> orderPendingFrom) {
        this.orderPendingFrom = orderPendingFrom;
    }

    public List<OrderReceived> getOrderReceivedFrom() {
        return orderReceivedFrom;
    }

    public void setOrderReceivedFrom(List<OrderReceived> orderReceivedFrom) {
        this.orderReceivedFrom = orderReceivedFrom;
    }

    public List<OrderOnTheWay> getOrderOnTheWayBy() {
        return orderOnTheWayBy;
    }

    public void setOrderOnTheWayBy(List<OrderOnTheWay> orderOnTheWayBy) {
        this.orderOnTheWayBy = orderOnTheWayBy;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Person(int id, String name, long phone, String userName, String password, String email, byte[] image, int accountBalance, String role, Address address, List<Visit> visits) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.address = address;
        this.visits = visits;
    }

    public Person(String name, long phone, String userName, String password, String email, byte[] image, int accountBalance, String role, Address address, List<OrderRequested> orderRequested) {
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.address = address;
        this.orderRequested = orderRequested;
    }

    public Person(String name, long phone, String userName, String password, String email, int accountBalance, String role, Address address) {
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accountBalance = accountBalance;
        this.role = role;
        this.address = address;
    }

    public Person(String name, long phone, String userName, String password, String email, byte[] image, int accountBalance, String role, Address address) {
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.address = address;
    }

    public Person(int id, String name, long phone, String userName, String password, String email, byte[] image, int accountBalance, String role, Address address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.address = address;
    }

    public Person() {
    }

    public Person(int id, String name, long phone, String userName, String password, String email, byte[] image, int accountBalance, String role, Address address, List<OrderRequested> orderRequested, List<OrderCompletedByUser> orderCompletedByUser, List<OrderCompleted> orderCompleted, List<OrderPending> orderPending, List<OrderReceived> orderReceived, List<OrderOnTheWay> orderOnTheWay, List<OrderCompletedByUser> orderCompletedByUserFor, List<OrderCompleted> orderCompletedBy, List<OrderPending> orderPendingFrom, List<OrderReceived> orderReceivedFrom, List<OrderOnTheWay> orderOnTheWayBy, List<Visit> visits) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.address = address;
        this.orderRequested = orderRequested;
        this.orderCompletedByUser = orderCompletedByUser;
        this.orderCompleted = orderCompleted;
        this.orderPending = orderPending;
        this.orderReceived = orderReceived;
        this.orderOnTheWay = orderOnTheWay;
        this.orderCompletedByUserFor = orderCompletedByUserFor;
        this.orderCompletedBy = orderCompletedBy;
        this.orderPendingFrom = orderPendingFrom;
        this.orderReceivedFrom = orderReceivedFrom;
        this.orderOnTheWayBy = orderOnTheWayBy;
        this.visits = visits;
    }
}