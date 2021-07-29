package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderPending;
import com.example.smartparceling.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Order;

@Component
public interface OrderPendingRepository extends JpaRepository<OrderPending,Integer> {
    public OrderPending findOrderPendingByOrder(Orders order);
    public OrderPending findOrderPendingById(int id);
}
