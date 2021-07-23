package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderReceivedRepository extends JpaRepository<OrderReceived,Integer> {
}
