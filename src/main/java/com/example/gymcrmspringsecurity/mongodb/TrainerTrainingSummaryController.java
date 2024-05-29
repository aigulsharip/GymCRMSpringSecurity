package com.example.gymcrmspringsecurity.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerTrainingSummaryController {

    @Autowired
    private TrainerTrainingSummaryService trainerTrainingSummaryService;

    @PostMapping("/events")
    public void handleEvent(@RequestBody TrainingEvent event) {
        trainerTrainingSummaryService.processEvent(event);
    }

    @GetMapping("/{username}")
    public ResponseEntity<TrainerTrainingSummary> getTrainerTrainingSummaryByUsername(@PathVariable String username) {
        TrainerTrainingSummary trainerTrainingSummary = trainerTrainingSummaryService.getTrainerTrainingSummaryByUsername(username);
        if (trainerTrainingSummary != null) {
            return ResponseEntity.ok(trainerTrainingSummary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TrainerTrainingSummary>> getAllTrainerTrainingSummaries() {
        List<TrainerTrainingSummary> trainerTrainingSummaries = trainerTrainingSummaryService.getAllTrainerTrainingSummaries();
        return ResponseEntity.ok(trainerTrainingSummaries);
    }
}

