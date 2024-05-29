package com.example.gymcrmspringsecurity.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerTrainingSummaryService {

    @Autowired
    private TrainerTrainingSummaryRepository trainerTrainingSummaryRepository;

    public void processEvent(TrainingEvent event) {
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
            trainerTrainingSummary.setStatus(event.getStatus());
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
    }

    public TrainerTrainingSummary getTrainerTrainingSummaryByUsername(String username) {
        Optional<TrainerTrainingSummary> trainerOptional = trainerTrainingSummaryRepository.findByUsername(username);
        return trainerOptional.orElse(null);
    }

    public List<TrainerTrainingSummary> getAllTrainerTrainingSummaries() {
        return trainerTrainingSummaryRepository.findAll();
    }
}
