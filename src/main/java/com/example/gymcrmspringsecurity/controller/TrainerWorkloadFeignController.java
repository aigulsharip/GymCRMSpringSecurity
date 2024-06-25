package com.example.gymcrmspringsecurity.controller;

import com.example.gymcrmspringsecurity.entity.feign.StatusLog;
import com.example.gymcrmspringsecurity.entity.feign.TrainerSummary;
import com.example.gymcrmspringsecurity.entity.feign.TrainerWorkload;
import com.example.gymcrmspringsecurity.feign.TrainerWorkloadFeignClient;
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
    private final TrainerWorkloadFeignClient trainingFeignClient;


    @GetMapping("/status-check")
    public ResponseEntity<String> check () {
        return trainingFeignClient.sayHello();
    }

    @GetMapping("/message")
    public StatusLog displayMessage () {
        StatusLog statusLog = new StatusLog();
        statusLog.setMessage(trainingFeignClient.displayMessage());
        return statusLog;
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

    @GetMapping("/trainer/workload/{id}")
    public ResponseEntity<TrainerWorkload> getWorkloadById(@PathVariable Long id) {
        log.info("Received request to get trainer workload with ID: {}", id);

        ResponseEntity<TrainerWorkload> response = trainingFeignClient.getWorkloadById(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            TrainerWorkload workload = response.getBody();
            log.info("Trainer workload found with ID: {}", id);
            return ResponseEntity.ok().body(workload);
        } else {
            log.error("Trainer workload with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/monthly")
    public ResponseEntity<List<TrainerSummary>> getMonthlySummaries() {
        return trainingFeignClient.getMonthlySummaries();

    }




}