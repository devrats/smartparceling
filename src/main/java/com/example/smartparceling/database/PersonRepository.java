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

@Component
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonByUserName(String userName);

    Person findPersonById(int id);

}