package com.example.gymcrmspringsecurity.mongodb.dto;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TrainingEvent {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    private boolean trainerStatus;

    @NotNull(message = "Training date cannot be null")
    @Future(message = "Training date must be in the future")
    private LocalDate trainingDate;

    @Min(value = 1, message = "Training duration must be at least 1 hour")
    private int trainingDuration;
}
