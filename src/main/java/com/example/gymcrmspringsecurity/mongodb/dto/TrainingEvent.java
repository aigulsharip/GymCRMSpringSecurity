package com.example.gymcrmspringsecurity.mongodb.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TrainingEvent {
    private String username;
    private String firstName;
    private String lastName;
    private boolean trainerStatus;
    private LocalDate trainingDate;
    private int trainingDuration;
}
