package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderRequested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRequestedRepository extends JpaRepository<OrderRequested,Integer> {
}
