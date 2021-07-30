/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 30-Jul-21
 *   Time: 9:06 PM
 *   File: Mobile.java
 */

package com.example.smartparceling.email;

import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class Mobile {
    public String sendSms(String otp,String phone) {
        try {
            // Construct data
            String apiKey = "apikey=" + "NTYzNjYxNzczNTU3NDQ0NjM3NGE0ZjY5Njk0NjMyNDM=";
            String message = "&message=" + otp;
            String sender = "&sender=" + "smart parceling";
            String numbers = "&numbers=" + phone;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }
}