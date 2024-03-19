package com.example.gymcrmspringsecurity.repository;

import com.example.gymcrmspringsecurity.entity.trainee.Trainee;
import com.example.gymcrmspringsecurity.entity.training.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByTrainee(Trainee trainee);



}

