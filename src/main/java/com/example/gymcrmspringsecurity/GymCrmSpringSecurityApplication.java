package com.example.gymcrmspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GymCrmSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymCrmSpringSecurityApplication.class, args);
    }

}
