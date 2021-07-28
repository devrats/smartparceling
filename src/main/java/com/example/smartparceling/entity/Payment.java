/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 27-Jul-21
 *   Time: 11:50 AM
 *   File: Payment.java
 */

package com.example.smartparceling.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class Payment {

    @Id
    private String paymentId;
    private String transactionId;
    private float amount;
    private String taxReceipt;
    private String status;
    private Date date;
    @ManyToOne
    private Person person;

    public Payment(String paymentId, float amount, String taxReceipt, String status, Date date) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.taxReceipt = taxReceipt;
        this.status = status;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", taxReceipt='" + taxReceipt + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment(String paymentId, float amount, String taxReceipt, String status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.taxReceipt = taxReceipt;
        this.status = status;
    }

    public Payment(String paymentId, float amount, String taxReceipt, String status, Person person) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.taxReceipt = taxReceipt;
        this.status = status;
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Float.compare(payment.getAmount(), getAmount()) == 0 && Objects.equals(getPaymentId(), payment.getPaymentId()) && Objects.equals(getTransactionId(), payment.getTransactionId()) && Objects.equals(getTaxReceipt(), payment.getTaxReceipt()) && Objects.equals(getStatus(), payment.getStatus()) && Objects.equals(getPerson(), payment.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaymentId(), getTransactionId(), getAmount(), getTaxReceipt(), getStatus(), getPerson());
    }

    public Payment() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTaxReceipt() {
        return taxReceipt;
    }

    public void setTaxReceipt(String taxReceipt) {
        this.taxReceipt = taxReceipt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Payment(String paymentId, String transactionId, float amount, String taxReceipt, String status, Person person) {
        this.paymentId = paymentId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.taxReceipt = taxReceipt;
        this.status = status;
        this.person = person;
    }
}