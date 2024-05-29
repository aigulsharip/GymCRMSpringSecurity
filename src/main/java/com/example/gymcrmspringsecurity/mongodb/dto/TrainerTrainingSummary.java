package com.example.gymcrmspringsecurity.mongodb.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "trainerTrainingSummaries")
public class TrainerTrainingSummary {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private boolean trainerStatus;
    private List<YearSummary> years;
}
