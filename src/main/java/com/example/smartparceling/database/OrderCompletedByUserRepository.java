package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderCompletedByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderCompletedByUserRepository extends JpaRepository<OrderCompletedByUser,Integer> {
    
}
