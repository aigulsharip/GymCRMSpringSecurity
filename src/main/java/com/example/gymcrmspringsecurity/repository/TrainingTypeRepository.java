package com.example.gymcrmspringsecurity.repository;

import com.example.gymcrmspringsecurity.entity.trainingType.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
}
