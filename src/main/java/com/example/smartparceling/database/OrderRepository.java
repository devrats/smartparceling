/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 8:52 AM
 *   File: OrderRepository.java
 */

package com.example.smartparceling.database;

import com.example.smartparceling.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    public Orders findOrdersById(int id);
}