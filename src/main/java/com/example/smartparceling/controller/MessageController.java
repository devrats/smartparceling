/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 02-Oct-21
 *   Time: 5:16 PM
 *   File: MessageController.java
 */

package com.example.smartparceling.controller;

import com.example.smartparceling.entity.Message1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message/{urls}")
    @ResponseBody
    public void message(@DestinationVariable String urls, @Payload Message1 message, Principal principal){
        simpMessagingTemplate.convertAndSendToUser(urls, "/queue/messages",message);
    }

}