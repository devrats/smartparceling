/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 6:44 PM
 *   File: OrderCompleted.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;

@Entity
public class OrderCompleted {

    @Id
    private int id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Person user;
    @OneToOne(cascade = CascadeType.ALL)
    private Orders order;
}