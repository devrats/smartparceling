package com.example.smartparceling.database;

import com.example.smartparceling.entity.OrderRequested;
import com.example.smartparceling.entity.Orders;
import com.example.smartparceling.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface OrderRequestedRepository extends JpaRepository<OrderRequested, Integer> {

    @Query("SELECT x.id from OrderRequested as x JOIN Orders as y ON " +
            "y.id = x.order.id JOIN Address as a ON a.orderFrom.id = y.id " +
            "INNER JOIN Address as b ON a.orderFrom.id = b.orderTo.id where " +
            "a.city=:cityFrom and a.state = :stateFrom and b.city= :cityTo and b.state = :stateTo and " +
            "y.weight<=:weight and y.date = :date")
    public List<Integer> findOrder(@Param("cityFrom") String city ,@Param("stateFrom") String stateFrom
            ,@Param("cityTo") String cityTo ,@Param("stateTo") String stateTo
                                   ,@Param("weight") float weight, @Param("date") Date date);

    public OrderRequested findOrderRequestedById(Integer orderRequestedInt);
    public OrderRequested findOrderRequestedByOrder(Orders order);
    public int countOrderRequestedByPerson(Person person);
}
