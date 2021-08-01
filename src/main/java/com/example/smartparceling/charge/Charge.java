/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 31-Jul-21
 *   Time: 8:27 PM
 *   File: Charge.java
 */

package com.example.smartparceling.charge;

public class Charge {

    public int charge(float distance){
        int charge = 0;
        if(distance<=100){
            charge = (int) (distance/2);
        } else if (distance<=200){
            charge = (int) (distance/3);
        } else if (distance<=300){
            charge = (int) (distance/4);
        } else if (distance<=500){
            charge = (int) (distance/5);
        }
        else if (distance<=1000){
            charge = (int) (distance/6);
        } else {
            charge = (int) (distance/7);
        }
        return charge;
    }
}