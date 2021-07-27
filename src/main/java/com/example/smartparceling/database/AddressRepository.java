/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 25-Jul-21
 *   Time: 4:38 PM
 *   File: AddressRepository.java
 */

package com.example.smartparceling.database;

import com.example.smartparceling.entity.Address;
import com.example.smartparceling.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Modifying
    @Query("update Address a set a.houseNumber=:h,a.street=:s," +
            "a.city=:c,a.state=:st,a.zip=:z where a.person=:id")
    @Transactional
    void update(@Param("h") String houseNumber, @Param("s") String street, @Param("c")
            String city, @Param("st") String state, @Param("z") String zip, @Param("id") int id);
}