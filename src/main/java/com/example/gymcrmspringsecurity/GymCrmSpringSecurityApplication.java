package com.example.gymcrmspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class GymCrmSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymCrmSpringSecurityApplication.class, args);
    }

}
