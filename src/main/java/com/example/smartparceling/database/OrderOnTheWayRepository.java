package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderOnTheWay;
import com.example.smartparceling.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderOnTheWayRepository extends JpaRepository<OrderOnTheWay, Integer> {
    public OrderOnTheWay findOrderOnTheWayById(int id);

    public OrderOnTheWay findOrderOnTheWayByOrder(Orders order);
}