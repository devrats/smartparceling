package com.example.smartparceling;

import com.example.smartparceling.email.Email;
import com.example.smartparceling.entity.Orders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class SmartparcelingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparcelingApplication.class, args);
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        System.out.println(number);
    }
}
