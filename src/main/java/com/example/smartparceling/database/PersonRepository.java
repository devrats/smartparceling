/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 22-Jul-21
 *   Time: 8:21 PM
 *   File: PersonRepository.java
 */

package com.example.smartparceling.database;

import com.example.smartparceling.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public List<Person> findPersonByUserName(String userName);
}