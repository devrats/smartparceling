/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 12:51 PM
 *   File: Address.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;

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
}