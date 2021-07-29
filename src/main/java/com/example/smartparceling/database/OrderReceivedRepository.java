package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderReceived;
import com.example.smartparceling.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderReceivedRepository extends JpaRepository<OrderReceived,Integer> {

    public OrderReceived findOrderReceivedById(int id);
    public List<OrderReceived> findOrderReceivedsByOrder(Orders order);
}
