/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 12:48 PM
 *   File: Person.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import java.util.List;

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

}