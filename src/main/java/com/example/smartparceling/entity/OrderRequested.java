/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 6:44 PM
 *   File: OrderRequested.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;

@Entity
public class OrderRequested {

    @Id
    private int id;
    @ManyToOne
    private Person person;
    @OneToOne(cascade = CascadeType.ALL)
    private Orders order;

}