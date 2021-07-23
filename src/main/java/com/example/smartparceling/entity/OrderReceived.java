/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 7:55 AM
 *   File: OrderReceived.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;

@Entity
public class OrderReceived {

    @Id
    private int id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Person owner;
    @OneToOne(cascade = CascadeType.ALL)
    private Orders order;
}