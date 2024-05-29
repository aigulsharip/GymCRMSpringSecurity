package com.example.gymcrmspringsecurity.mongodb.service;

import com.example.gymcrmspringsecurity.mongodb.repository.TrainerTrainingSummaryRepository;
import com.example.gymcrmspringsecurity.mongodb.dto.TrainingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainerTrainingSummaryService {

    @Autowired
    private TrainerTrainingSummaryRepository trainerTrainingSummaryRepository;

    public void processEvent(TrainingEvent event) {
        log.info("Processing event for username: {}", event.getUsername());
        String username = event.getUsername();
        int trainingDuration = event.getTrainingDuration();
        LocalDate trainingDate = event.getTrainingDate();

        int year = trainingDate.getYear();
        int month = trainingDate.getMonthValue();

        Optional<TrainerTrainingSummary> trainerOptional = trainerTrainingSummaryRepository.findByUsername(username);

        TrainerTrainingSummary trainerTrainingSummary;
        if (trainerOptional.isEmpty()) {
            trainerTrainingSummary = new TrainerTrainingSummary();
            trainerTrainingSummary.setUsername(username);
            trainerTrainingSummary.setFirstName(event.getFirstName());
            trainerTrainingSummary.setLastName(event.getLastName());
            trainerTrainingSummary.setTrainerStatus(event.isTrainerStatus()); // Corrected line

            trainerTrainingSummary.setYears(new ArrayList<>());

            YearSummary yearSummary = new YearSummary();
            yearSummary.setYear(year);
            yearSummary.setMonths(new ArrayList<>());

            MonthSummary monthSummary = new MonthSummary();
            monthSummary.setMonth(month);
            monthSummary.setTrainingSummaryDuration(trainingDuration);

            yearSummary.getMonths().add(monthSummary);
            trainerTrainingSummary.getYears().add(yearSummary);
        } else {
            trainerTrainingSummary = trainerOptional.get();

            YearSummary yearSummary = trainerTrainingSummary.getYears().stream()
                    .filter(y -> y.getYear() == year)
                    .findFirst()
                    .orElseGet(() -> {
                        YearSummary newYearSummary = new YearSummary();
                        newYearSummary.setYear(year);
                        newYearSummary.setMonths(new ArrayList<>());
                        trainerTrainingSummary.getYears().add(newYearSummary);
                        return newYearSummary;
                    });

            MonthSummary monthSummary = yearSummary.getMonths().stream()
                    .filter(m -> m.getMonth() == month)
                    .findFirst()
                    .orElseGet(() -> {
                        MonthSummary newMonthSummary = new MonthSummary();
                        newMonthSummary.setMonth(month);
                        yearSummary.getMonths().add(newMonthSummary);
                        return newMonthSummary;
                    });

            monthSummary.setTrainingSummaryDuration(monthSummary.getTrainingSummaryDuration() + trainingDuration);
        }

        trainerTrainingSummaryRepository.save(trainerTrainingSummary);
        log.info("Event processed successfully for username: {}", event.getUsername());
    }


    public TrainerTrainingSummary getTrainerTrainingSummaryByUsername(String username) {
        log.info("Fetching trainer training summary for username: {}", username);
        Optional<TrainerTrainingSummary> trainerOptional = trainerTrainingSummaryRepository.findByUsername(username);
        return trainerOptional.orElse(null);
    }

    public List<TrainerTrainingSummary> getAllTrainerTrainingSummaries() {
        log.info("Fetching all trainer training summaries");
        return trainerTrainingSummaryRepository.findAll();
    }
}
