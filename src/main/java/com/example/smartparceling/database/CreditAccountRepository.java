/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 30-Jul-21
 *   Time: 6:57 PM
 *   File: CreditAccountRepository.java
 */

package com.example.smartparceling.database;

import com.example.smartparceling.entity.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface CreditAccountRepository extends JpaRepository<CreditAccount, Integer> {
}