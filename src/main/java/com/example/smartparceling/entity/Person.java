/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 12:48 PM
 *   File: Person.java
 */

package com.example.smartparceling.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "This field cannot be blank")
    private String name;
    @NotBlank(message = "This field cannot be blank")
    private String phone;
    @NotBlank(message = "This field cannot be blank")
    private String userName;
    @NotBlank(message = "This field cannot be blank")
    @Size(min = 8, message = "Password must contain 8 to 20 character")
    private String password;
    @NotBlank(message = "This field cannot be blank")
    private String email;
    private byte[] image;
    private int accountBalance;
    private String role;
    @OneToMany(mappedBy = "person")
    private List<Payment> payment;
    @AssertTrue(message = "Must agree term and condition")
    private boolean agree;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(mappedBy = "person")
    private List<OrderRequested> orderRequested;
    @OneToMany(mappedBy = "person")
    private List<OrderCompletedByUser> orderCompletedByUser;
    @OneToMany(mappedBy = "person")
    private List<OrderCompleted> orderCompleted;
    @OneToMany(mappedBy = "person")
    private List<OrderPending> orderPending;
    @OneToMany(mappedBy = "person")
    private List<OrderReceived> orderReceived;
    @OneToMany(mappedBy = "person")
    private List<OrderOnTheWay> orderOnTheWay;
    @OneToMany(mappedBy = "owner")
    private List<OrderCompletedByUser> orderCompletedByUserFor;
    @OneToMany(mappedBy = "user")
    private List<OrderCompleted> orderCompletedBy;
    @OneToMany(mappedBy = "owner")
    private List<OrderPending> orderPendingFrom;
    @OneToMany(mappedBy = "owner")
    private List<OrderReceived> orderReceivedFrom;
    @OneToMany(mappedBy = "user")
    private List<OrderOnTheWay> orderOnTheWayBy;
    @OneToMany(mappedBy = "person")
    private List<Visit> visits;
    private Boolean isAdhaarVerified;
    private byte[] adhaar;
    private boolean isEmailVerified;
    private boolean isPhoneVerified;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    @OneToMany(mappedBy = "person")
    private List<Message> message;
    @OneToMany(mappedBy = "person")
    private List<CreditAccount> creditAccounts;

    public List<CreditAccount> getCreditAccounts() {
        return creditAccounts;
    }

    public void setCreditAccounts(List<CreditAccount> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && getAccountBalance() == person.getAccountBalance() && isAgree() == person.isAgree() && isEmailVerified() == person.isEmailVerified() && isPhoneVerified() == person.isPhoneVerified() && isAccountNonExpired() == person.isAccountNonExpired() && isAccountNonLocked() == person.isAccountNonLocked() && isCredentialsNonExpired() == person.isCredentialsNonExpired() && isEnabled() == person.isEnabled() && Objects.equals(getName(), person.getName()) && Objects.equals(getPhone(), person.getPhone()) && Objects.equals(getUserName(), person.getUserName()) && Objects.equals(getPassword(), person.getPassword()) && Objects.equals(getEmail(), person.getEmail()) && Arrays.equals(getImage(), person.getImage()) && Objects.equals(getRole(), person.getRole()) && Objects.equals(getPayment(), person.getPayment()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getOrderRequested(), person.getOrderRequested()) && Objects.equals(getOrderCompletedByUser(), person.getOrderCompletedByUser()) && Objects.equals(getOrderCompleted(), person.getOrderCompleted()) && Objects.equals(getOrderPending(), person.getOrderPending()) && Objects.equals(getOrderReceived(), person.getOrderReceived()) && Objects.equals(getOrderOnTheWay(), person.getOrderOnTheWay()) && Objects.equals(getOrderCompletedByUserFor(), person.getOrderCompletedByUserFor()) && Objects.equals(getOrderCompletedBy(), person.getOrderCompletedBy()) && Objects.equals(getOrderPendingFrom(), person.getOrderPendingFrom()) && Objects.equals(getOrderReceivedFrom(), person.getOrderReceivedFrom()) && Objects.equals(getOrderOnTheWayBy(), person.getOrderOnTheWayBy()) && Objects.equals(getVisits(), person.getVisits()) && Objects.equals(isAdhaarVerified, person.isAdhaarVerified) && Arrays.equals(getAdhaar(), person.getAdhaar());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getPhone(), getUserName(), getPassword(), getEmail(), getAccountBalance(), getRole(), getPayment(), isAgree(), getAddress(), getOrderRequested(), getOrderCompletedByUser(), getOrderCompleted(), getOrderPending(), getOrderReceived(), getOrderOnTheWay(), getOrderCompletedByUserFor(), getOrderCompletedBy(), getOrderPendingFrom(), getOrderReceivedFrom(), getOrderOnTheWayBy(), getVisits(), isAdhaarVerified, isEmailVerified(), isPhoneVerified(), isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled());
        result = 31 * result + Arrays.hashCode(getImage());
        result = 31 * result + Arrays.hashCode(getAdhaar());
        return result;
    }

    public Boolean getAdhaarVerified() {
        return isAdhaarVerified;
    }

    public void setAdhaarVerified(Boolean adhaarVerified) {
        isAdhaarVerified = adhaarVerified;
    }

    public byte[] getAdhaar() {
        return adhaar;
    }

    public Person(int id, String name, String phone, String userName, String password, String email, int accountBalance, String role, boolean agree, Address address, byte[] adhaar, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accountBalance = accountBalance;
        this.role = role;
        this.agree = agree;
        this.address = address;
        this.adhaar = adhaar;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public void setAdhaar(byte[] adhaar) {
        this.adhaar = adhaar;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Person(String name, String phone, String userName, String password, String email, byte[] image, int accountBalance, String role, List<Payment> payment, boolean agree, Address address) {
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.payment = payment;
        this.agree = agree;
        this.address = address;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public Person(int id, String name, String phone, String userName, String password, String email, byte[] image, int accountBalance, String role, boolean agree, Address address, List<Visit> visits) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.agree = agree;
        this.address = address;
        this.visits = visits;
    }

    public Person(String name, String phone, String userName, String password, String email, byte[] image, int accountBalance, String role, boolean agree, Address address, List<OrderRequested> orderRequested) {
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.agree = agree;
        this.address = address;
        this.orderRequested = orderRequested;
    }

    public Person(String name, String phone, String userName, String password, String email, byte[] image, int accountBalance, String role, boolean agree, Address address) {
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.agree = agree;
        this.address = address;
    }

    public Person() {
    }

    public Person(int id, String name, String phone, String userName, String password, String email, byte[] image, int accountBalance, String role, boolean agree, Address address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.agree = agree;
        this.address = address;
    }

    public Person(int id, String name, String phone, String userName, String password, String email, byte[] image, int accountBalance, String role, boolean agree, Address address, List<OrderRequested> orderRequested, List<OrderCompletedByUser> orderCompletedByUser, List<OrderCompleted> orderCompleted, List<OrderPending> orderPending, List<OrderReceived> orderReceived, List<OrderOnTheWay> orderOnTheWay, List<OrderCompletedByUser> orderCompletedByUserFor, List<OrderCompleted> orderCompletedBy, List<OrderPending> orderPendingFrom, List<OrderReceived> orderReceivedFrom, List<OrderOnTheWay> orderOnTheWayBy, List<Visit> visits) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.image = image;
        this.accountBalance = accountBalance;
        this.role = role;
        this.agree = agree;
        this.address = address;
        this.orderRequested = orderRequested;
        this.orderCompletedByUser = orderCompletedByUser;
        this.orderCompleted = orderCompleted;
        this.orderPending = orderPending;
        this.orderReceived = orderReceived;
        this.orderOnTheWay = orderOnTheWay;
        this.orderCompletedByUserFor = orderCompletedByUserFor;
        this.orderCompletedBy = orderCompletedBy;
        this.orderPendingFrom = orderPendingFrom;
        this.orderReceivedFrom = orderReceivedFrom;
        this.orderOnTheWayBy = orderOnTheWayBy;
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", image=" + Arrays.toString(image) +
                ", accountBalance=" + accountBalance +
                ", role='" + role + '\'' +
                ", agree=" + agree +
                ", address=" + address +
                ", orderRequested=" + orderRequested +
                ", orderCompletedByUser=" + orderCompletedByUser +
                ", orderCompleted=" + orderCompleted +
                ", orderPending=" + orderPending +
                ", orderReceived=" + orderReceived +
                ", orderOnTheWay=" + orderOnTheWay +
                ", orderCompletedByUserFor=" + orderCompletedByUserFor +
                ", orderCompletedBy=" + orderCompletedBy +
                ", orderPendingFrom=" + orderPendingFrom +
                ", orderReceivedFrom=" + orderReceivedFrom +
                ", orderOnTheWayBy=" + orderOnTheWayBy +
                ", visits=" + visits +
                '}';
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderRequested> getOrderRequested() {
        return orderRequested;
    }

    public void setOrderRequested(List<OrderRequested> orderRequested) {
        this.orderRequested = orderRequested;
    }

    public List<OrderCompletedByUser> getOrderCompletedByUser() {
        return orderCompletedByUser;
    }

    public void setOrderCompletedByUser(List<OrderCompletedByUser> orderCompletedByUser) {
        this.orderCompletedByUser = orderCompletedByUser;
    }

    public List<OrderCompleted> getOrderCompleted() {
        return orderCompleted;
    }

    public void setOrderCompleted(List<OrderCompleted> orderCompleted) {
        this.orderCompleted = orderCompleted;
    }

    public List<OrderPending> getOrderPending() {
        return orderPending;
    }

    public void setOrderPending(List<OrderPending> orderPending) {
        this.orderPending = orderPending;
    }

    public List<OrderReceived> getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(List<OrderReceived> orderReceived) {
        this.orderReceived = orderReceived;
    }

    public List<OrderOnTheWay> getOrderOnTheWay() {
        return orderOnTheWay;
    }

    public void setOrderOnTheWay(List<OrderOnTheWay> orderOnTheWay) {
        this.orderOnTheWay = orderOnTheWay;
    }

    public List<OrderCompletedByUser> getOrderCompletedByUserFor() {
        return orderCompletedByUserFor;
    }

    public void setOrderCompletedByUserFor(List<OrderCompletedByUser> orderCompletedByUserFor) {
        this.orderCompletedByUserFor = orderCompletedByUserFor;
    }

    public List<OrderCompleted> getOrderCompletedBy() {
        return orderCompletedBy;
    }

    public void setOrderCompletedBy(List<OrderCompleted> orderCompletedBy) {
        this.orderCompletedBy = orderCompletedBy;
    }

    public List<OrderPending> getOrderPendingFrom() {
        return orderPendingFrom;
    }

    public void setOrderPendingFrom(List<OrderPending> orderPendingFrom) {
        this.orderPendingFrom = orderPendingFrom;
    }

    public List<OrderReceived> getOrderReceivedFrom() {
        return orderReceivedFrom;
    }

    public void setOrderReceivedFrom(List<OrderReceived> orderReceivedFrom) {
        this.orderReceivedFrom = orderReceivedFrom;
    }

    public List<OrderOnTheWay> getOrderOnTheWayBy() {
        return orderOnTheWayBy;
    }

    public void setOrderOnTheWayBy(List<OrderOnTheWay> orderOnTheWayBy) {
        this.orderOnTheWayBy = orderOnTheWayBy;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

}