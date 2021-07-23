package com.example.smartparceling;

import com.example.smartparceling.entity.Orders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartparcelingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparcelingApplication.class, args);
        Orders orders = new Orders();
    }

}
