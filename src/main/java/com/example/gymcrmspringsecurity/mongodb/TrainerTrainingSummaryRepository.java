package com.example.gymcrmspringsecurity.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface TrainerTrainingSummaryRepository extends MongoRepository<TrainerTrainingSummary, String> {
    Optional<TrainerTrainingSummary> findByUsername(String username);
}
