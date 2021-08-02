package com.example.smartparceling.database;

import com.example.smartparceling.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface VisitRepository extends JpaRepository<Visit,Integer> {


    @Query("select y.person.id from Visit as y JOIN Address as a ON " +
            "y.id = a.visitFrom.id INNER JOIN Address AS b ON a.visitFrom.id = b.visitTo.id where " +
            "a.city=:cityFrom and a.state = :stateFrom and b.city= :cityTo and b.state = :stateTo and " +
            "y.weight>=:weight and y.date = :date")
    public List<Integer> findVisitByPerson(@Param("cityFrom") String city , @Param("stateFrom") String stateFrom
            , @Param("cityTo") String cityTo , @Param("stateTo") String stateTo
            , @Param("weight") float weight, @Param("date") Date date);

    public Visit findVisitById(int id);
}
