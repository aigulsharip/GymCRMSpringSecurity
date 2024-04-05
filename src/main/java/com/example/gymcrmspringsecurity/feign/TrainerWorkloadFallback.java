package com.example.gymcrmspringsecurity.feign;

import com.example.gymcrmspringsecurity.entity.feign.TrainerSummary;
import com.example.gymcrmspringsecurity.entity.feign.TrainerWorkload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainerWorkloadFallback  implements TrainerWorkloadFeignClient{
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Training Microservice is down");
    }

    public String displayMessage() {
        return "This fallback message";
    }

    public ResponseEntity<List<TrainerWorkload>> getAllTrainerWorkloads() {
        return ResponseEntity.ok(new ArrayList<>());

    }

    public ResponseEntity<String> updateWorkload(TrainerWorkload request) {
        return ResponseEntity.ok("Training Microservice is down, trainer's workload not updated");
    }

    public ResponseEntity<List<TrainerSummary>> getMonthlySummaries() {
        return ResponseEntity.ok(new ArrayList<>());
    }


}
