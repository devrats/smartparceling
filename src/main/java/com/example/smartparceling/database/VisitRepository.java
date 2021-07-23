package com.example.smartparceling.database;

import com.example.smartparceling.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface VisitRepository extends JpaRepository<Visit,Integer> {
}
