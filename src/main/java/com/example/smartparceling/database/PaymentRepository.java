/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 27-Jul-21
 *   Time: 12:11 PM
 *   File: PaymentRepository.java
 */

package com.example.smartparceling.database;

import com.example.smartparceling.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {

    public Payment findPaymentByPaymentId(String razorpay_order_id);
}