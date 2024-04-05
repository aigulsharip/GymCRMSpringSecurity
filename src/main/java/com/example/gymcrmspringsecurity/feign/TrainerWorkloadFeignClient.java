package com.example.gymcrmspringsecurity.feign;

import com.example.gymcrmspringsecurity.entity.feign.TrainerSummary;
import com.example.gymcrmspringsecurity.entity.feign.TrainerWorkload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@FeignClient(name = "training-feign-client", url = "${training.feign.client.url}")
@FeignClient(value = "trainer-gym-microservice", fallback = TrainerWorkloadFallback.class)
public interface TrainerWorkloadFeignClient {

    @GetMapping("/trainings/status-check")
    ResponseEntity<String> sayHello();

    @GetMapping("/trainings/message")
    String displayMessage();

    @GetMapping("/trainings/trainer/workload")
    ResponseEntity<List<TrainerWorkload>> getAllTrainerWorkloads();


    @PostMapping("/trainings/trainer/workload")
    ResponseEntity<String> updateWorkload(@RequestBody TrainerWorkload request);

    @GetMapping("/trainings/monthly")
    ResponseEntity<List<TrainerSummary>> getMonthlySummaries();

}
