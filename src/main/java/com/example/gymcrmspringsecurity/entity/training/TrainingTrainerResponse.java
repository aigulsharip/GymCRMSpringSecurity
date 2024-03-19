package com.example.gymcrmspringsecurity.entity.training;

import com.example.gymcrmspringsecurity.entity.trainingType.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingTrainerResponse {
    private String trainingName;
    private LocalDate trainingDate;
    private TrainingType trainingType;
    private int trainingDuration;
    private String traineeName;
}
