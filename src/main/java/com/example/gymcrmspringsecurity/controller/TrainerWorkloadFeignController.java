package com.example.gymcrmspringsecurity.controller;

import com.example.gymcrmspringsecurity.entity.feign.TrainerSummary;
import com.example.gymcrmspringsecurity.entity.feign.TrainerWorkload;
import com.example.gymcrmspringsecurity.feign.TrainingFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings-feign/")
@Slf4j
@RequiredArgsConstructor
public class TrainerWorkloadFeignController {
    private final TrainingFeignClient trainingFeignClient;


    @GetMapping("/status-check")
    public ResponseEntity<String> check () {
        return trainingFeignClient.sayHello();
    }


    @GetMapping("/trainer/workload")
    public ResponseEntity<List<TrainerWorkload>> getAllTrainerWorkloads() {
        log.info("Received request to get all trainer workloads");
        return trainingFeignClient.getAllTrainerWorkloads();

    }


    @PostMapping("/trainer/workload")
    public ResponseEntity<String> updateWorkload(@RequestBody TrainerWorkload request) {
        log.info("Received request to update trainer workload: {}", request);
        trainingFeignClient.updateWorkload(request);
        log.info("Trainer workload updated successfully: {}", request);
        return ResponseEntity.ok("Workload updated successfully");
    }


    @GetMapping("/monthly")
    public ResponseEntity<List<TrainerSummary>> getMonthlySummaries() {
        return trainingFeignClient.getMonthlySummaries();

    }




}