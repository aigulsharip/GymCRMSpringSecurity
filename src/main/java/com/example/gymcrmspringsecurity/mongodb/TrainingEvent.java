package com.example.gymcrmspringsecurity.mongodb;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TrainingEvent {
    private String username;
    private String firstName;
    private String lastName;
    private String status;
    private LocalDate trainingDate;
    private int trainingDuration;
}
