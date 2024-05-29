package com.example.gymcrmspringsecurity.mongodb.controller;

import com.example.gymcrmspringsecurity.mongodb.dto.TrainerTrainingSummary;
import com.example.gymcrmspringsecurity.mongodb.dto.TrainingEvent;
import com.example.gymcrmspringsecurity.mongodb.service.TrainerTrainingSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers-summary")
@Slf4j
public class TrainerTrainingSummaryController {

    @Autowired
    private TrainerTrainingSummaryService trainerTrainingSummaryService;

    @PostMapping("/events")
    public ResponseEntity<String> handleEvent(@RequestBody TrainingEvent event) {
        log.info("Handling event for username: {}", event.getUsername());
        trainerTrainingSummaryService.processEvent(event);
        log.info("Event handled successfully for username: {}", event.getUsername());
        return ResponseEntity.ok("Training event created successfully for username: " + event.getUsername());
    }


    @GetMapping("/{username}")
    public ResponseEntity<TrainerTrainingSummary> getTrainerTrainingSummaryByUsername(@PathVariable String username) {
        log.info("Fetching trainer training summary for username: {}", username);
        TrainerTrainingSummary trainerTrainingSummary = trainerTrainingSummaryService.getTrainerTrainingSummaryByUsername(username);
        if (trainerTrainingSummary != null) {
            log.info("Trainer training summary found for username: {}", username);
            return ResponseEntity.ok(trainerTrainingSummary);
        } else {
            log.warn("Trainer training summary not found for username: {}", username);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TrainerTrainingSummary>> getAllTrainerTrainingSummaries() {
        log.info("Fetching all trainer training summaries");
        List<TrainerTrainingSummary> trainerTrainingSummaries = trainerTrainingSummaryService.getAllTrainerTrainingSummaries();
        return ResponseEntity.ok(trainerTrainingSummaries);
    }
}
