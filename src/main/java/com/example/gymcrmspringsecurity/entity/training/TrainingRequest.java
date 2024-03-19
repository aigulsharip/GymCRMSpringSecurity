package com.example.gymcrmspringsecurity.entity.training;

import com.example.gymcrmspringsecurity.entity.trainingType.TrainingType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {
    @NotBlank(message = "Trainee username is required")
    private String traineeUsername;

    @NotBlank(message = "Trainer username is required")
    private String trainerUsername;

    @NotNull(message = "Training type is required")
    private TrainingType trainingType;

    @NotBlank(message = "Training name is required")
    private String trainingName;

    @NotNull(message = "Training date is required")
    @FutureOrPresent(message = "Training date must be in the past or present")
    private LocalDate trainingDate;

    @Positive(message = "Training duration must be a positive integer")
    private int trainingDuration;
}