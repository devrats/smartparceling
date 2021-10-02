/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 28-Sep-21
 *   Time: 7:43 PM
 *   File: Message1.java
 */

package com.example.smartparceling.entity;

import java.util.Objects;

public class Message1 {

    private String name;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message1)) return false;
        Message1 message1 = (Message1) o;
        return Objects.equals(getName(), message1.getName()) && Objects.equals(getText(), message1.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getText());
    }

    @Override
    public String toString() {
        return "Message1{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message1() {
    }

    public Message1(String name, String text) {
        this.name = name;
        this.text = text;
    }
}