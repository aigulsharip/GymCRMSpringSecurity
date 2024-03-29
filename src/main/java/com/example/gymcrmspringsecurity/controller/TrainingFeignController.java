package com.example.gymcrmspringsecurity.controller;

import com.example.gymcrmspringsecurity.feign.TrainingFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainings-feign/")
@Slf4j
@RequiredArgsConstructor
public class TrainingFeignController {
    private final TrainingFeignClient trainingFeignClient;


    @GetMapping("/status-check")
    public ResponseEntity<String> check () {
        return trainingFeignClient.sayHello();
    }
}