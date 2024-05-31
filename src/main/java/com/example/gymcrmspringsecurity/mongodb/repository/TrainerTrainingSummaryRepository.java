package com.example.gymcrmspringsecurity.mongodb.repository;


import com.example.gymcrmspringsecurity.mongodb.dto.TrainerTrainingSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerTrainingSummaryRepository extends MongoRepository<TrainerTrainingSummary, String> {
    Optional<TrainerTrainingSummary> findByUsername(String username);
}
