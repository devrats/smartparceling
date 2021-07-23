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
}