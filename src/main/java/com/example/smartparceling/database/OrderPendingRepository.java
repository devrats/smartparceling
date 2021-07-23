package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderPending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderPendingRepository extends JpaRepository<OrderPending,Integer> {
}
