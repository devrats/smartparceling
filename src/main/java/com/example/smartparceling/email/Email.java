/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 30-Jul-21
 *   Time: 2:08 PM
 *   File: Email.java
 */

package com.example.smartparceling.email;

public class Email {

    private String to;
    private String head = "Thanks For Dealing with us";
    private String name;
    private String email;
    private String phone;
    private String otp;
    private String msg = "Here are the details of the person you have to contact \n" +
            "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\n" +
            "please be polite to other user.\nwe are happy to help you and always there for you";
    private String head2 = "Verify your new Smart Parceling account";
    private String msg1 = "To verify your email address, please use the following One Time Password (OTP):\n" +
            "\n" +
            otp + "\n" +
            "\n" +
            "Do not share this OTP with anyone." + "\nSmart Parceling takes your account security very seriously. Smart Parceling Customer Service will never ask you to disclose or verify your Smart Parceling password, OTP, credit card, or banking account number. If you receive a suspicious email with a link to update your account information, do not click on the link—instead, report the email to Smart Parceling for investigation.\n" +
            "\n" +
            "Thank you!\n" +
            "\n";
    private String head3 = "You can report the person";

    public String getMsg1() {
        return msg1;
    }

    public String getHead3() {
        return head3;
    }

    public void setHead3(String head3) {
        this.head3 = head3;
    }

    public String getMsg3() {
        return msg3;
    }

    public void setMsg3(String msg3) {
        this.msg3 = msg3;
    }

    private String msg3 = "we are sending you details and photo identity proof of the user you want to report"
            + "\nwe are happy to help you and always there for you";

    public Email(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Email() {
    }

    public String getMsg(String name, String email, String phone) {
        return "Here are the details of the person you have to contact \n" +
                "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\n" +
                "please be polite to other user.\n we are happy to help you and always there for you";
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMsg() {
        return msg;
    }

    public String getHead2() {
        return head2;
    }

    public void setHead2(String head2) {
        this.head2 = head2;
    }

    public String getMsg1(String otp) {
        return "To verify your email address, please use the following One Time Password (OTP):\n" +
                "\n" +
                otp + "\n" +
                "\n" +
                "Do not share this OTP with anyone." + "\nSmart Parceling takes your account security very seriously. Smart Parceling Customer Service will never ask you to disclose or verify your Smart Parceling password, OTP, credit card, or banking account number. If you receive a suspicious email with a link to update your account information, do not click on the link—instead, report the email to Smart Parceling for investigation.\n" +
                "\n" +
                "Thank you!\n" +
                "\n";
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}