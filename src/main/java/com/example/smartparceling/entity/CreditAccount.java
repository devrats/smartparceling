/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 30-Jul-21
 *   Time: 6:37 PM
 *   File: CreditAccount.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class CreditAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "This field cannot be blank")
    private String accountNumber;
    @NotBlank(message = "This field cannot be blank")
    private String IFSCCode;
    @NotBlank(message = "This field cannot be blank")
    private String branchName;
    private String upi;
    @ManyToOne
    private Person person;
    private String status;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public CreditAccount(@NotBlank(message = "This field cannot be blank") String accountNumber, @NotBlank(message = "This field cannot be blank") String IFSCCode, @NotBlank(message = "This field cannot be blank") String branchName, String upi, Person person, String status, int amount) {
        this.accountNumber = accountNumber;
        this.IFSCCode = IFSCCode;
        this.branchName = branchName;
        this.upi = upi;
        this.person = person;
        this.status = status;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CreditAccount(@NotBlank(message = "This field cannot be blank") String accountNumber, @NotBlank(message = "This field cannot be blank") String IFSCCode, @NotBlank(message = "This field cannot be blank") String branchName) {
        this.accountNumber = accountNumber;
        this.IFSCCode = IFSCCode;
        this.branchName = branchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditAccount)) return false;
        CreditAccount that = (CreditAccount) o;
        return getId() == that.getId() && Objects.equals(getAccountNumber(), that.getAccountNumber()) && Objects.equals(getIFSCCode(), that.getIFSCCode()) && Objects.equals(getBranchName(), that.getBranchName()) && Objects.equals(getUpi(), that.getUpi()) && Objects.equals(getPerson(), that.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountNumber(), getIFSCCode(), getBranchName(), getUpi(), getPerson());
    }

    public int getId() {



        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CreditAccount() {
    }

    public CreditAccount(int id, String accountNumber, String IFSCCode, String branchName, String upi, Person person) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.IFSCCode = IFSCCode;
        this.branchName = branchName;
        this.upi = upi;
        this.person = person;
    }
}