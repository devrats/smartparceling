/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 29-Jul-21
 *   Time: 7:52 PM
 *   File: MessageRepository.java
 */

package com.example.smartparceling.database;

import com.example.smartparceling.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}