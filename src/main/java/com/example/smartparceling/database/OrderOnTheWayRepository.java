package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderOnTheWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderOnTheWayRepository extends JpaRepository<OrderOnTheWay,Integer> {
}
