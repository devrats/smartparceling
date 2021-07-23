/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 12:49 PM
 *   File: Visit.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import java.time.Period;
import java.util.Date;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Person person;
    @OneToOne(mappedBy = "visitFrom",cascade = CascadeType.ALL)
    private Address from;
    @OneToOne(mappedBy = "visitTo", cascade = CascadeType.ALL)
    private Address to;
    private float weight;
    private String via;
    private Date date;
}