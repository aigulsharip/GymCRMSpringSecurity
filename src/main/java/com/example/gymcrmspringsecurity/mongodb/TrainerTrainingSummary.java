package com.example.gymcrmspringsecurity.mongodb;

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
    private String status;
    private List<YearSummary> years;
}

@Data
class YearSummary {
    private int year;
    private List<MonthSummary> months;
}

@Data
class MonthSummary {
    private int month;
    private int trainingSummaryDuration;
}
