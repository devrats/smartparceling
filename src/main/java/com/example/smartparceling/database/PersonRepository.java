/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 8:21 PM
 *   File: PersonRepository.java
 */

package com.example.smartparceling.database;

import com.example.smartparceling.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Period;
import java.util.List;

@Component
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonByUserName(String userName);

    @Modifying
    @Query("update Person p set p.name=:n,p.email=:e,p.phone=:ph where p.userName=:u")
    @Transactional
    void update(@Param("n") String name, @Param("e") String email, @Param("ph")
            String phone, @Param("u") String userName);
}