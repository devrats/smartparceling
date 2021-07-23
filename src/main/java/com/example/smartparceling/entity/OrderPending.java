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
}