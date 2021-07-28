package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderRequested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface OrderRequestedRepository extends JpaRepository<OrderRequested, Integer> {

    @Query("from OrderRequested OrderReqyested where OrderReqyested.order.from.city = :a and OrderReqyested.order.to.city = :b and " +
            "OrderReqyested.order.weight <= :d and OrderReqyested.order.from.state = :e and OrderReqyested.order.to.state = :f")
    public List<OrderRequested> findOrder(@Param("a") String fromCity, @Param("b") String toCity,
                                          @Param("d") Float weight, @Param("e") String fromState, @Param("f")
                                                  String toState);
}
