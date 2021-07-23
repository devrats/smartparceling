package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface OrderCompletedRepository extends JpaRepository<OrderCompleted,Integer> {
}