/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 30-Jul-21
 *   Time: 2:08 PM
 *   File: EmailService.java
 */

package com.example.smartparceling.email;

import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String message) {

        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp,port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication passwordAuthentication = new PasswordAuthentication("devvvrats@gmail.com", "Devvrat@25");
                return passwordAuthentication;
            }
        });
        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom("devvvrats@gmail.com");
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}