package com.example.gymcrmspringsecurity.mongodb.service;

import com.example.gymcrmspringsecurity.mongodb.dto.TrainerTrainingSummary;
import com.example.gymcrmspringsecurity.mongodb.dto.TrainingEvent;
import com.example.gymcrmspringsecurity.mongodb.repository.TrainerTrainingSummaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerTrainingSummaryServiceTest {

    @Mock
    private TrainerTrainingSummaryRepository trainerTrainingSummaryRepository;

    @InjectMocks
    private TrainerTrainingSummaryService trainerTrainingSummaryService;

    @Test
    public void processEvent_NewTrainerTrainingSummary_SuccessfullyCreated() {
        TrainingEvent event = createTrainingEvent();
        when(trainerTrainingSummaryRepository.findByUsername(event.getUsername())).thenReturn(Optional.empty());
        when(trainerTrainingSummaryRepository.save(any(TrainerTrainingSummary.class))).thenReturn(eventToTrainerTrainingSummary(event));

        trainerTrainingSummaryService.processEvent(event);

        verify(trainerTrainingSummaryRepository, times(1)).findByUsername(event.getUsername());
        verify(trainerTrainingSummaryRepository, times(1)).save(any(TrainerTrainingSummary.class));
    }

    @Test
    public void processEvent_ExistingTrainerTrainingSummary_SuccessfullyUpdated() {
        TrainingEvent event = createTrainingEvent();
        TrainerTrainingSummary existingSummary = eventToTrainerTrainingSummary(event);
        when(trainerTrainingSummaryRepository.findByUsername(event.getUsername())).thenReturn(Optional.of(existingSummary));
        when(trainerTrainingSummaryRepository.save(any(TrainerTrainingSummary.class))).thenReturn(existingSummary);

        trainerTrainingSummaryService.processEvent(event);

        verify(trainerTrainingSummaryRepository, times(1)).findByUsername(event.getUsername());
        verify(trainerTrainingSummaryRepository, times(1)).save(any(TrainerTrainingSummary.class));
    }

    @Test
    public void getTrainerTrainingSummaryByUsername_ExistingTrainer_SuccessfullyReturned() {
        String username = "testUsername";
        TrainerTrainingSummary expectedSummary = new TrainerTrainingSummary();
        expectedSummary.setUsername(username);
        when(trainerTrainingSummaryRepository.findByUsername(username)).thenReturn(Optional.of(expectedSummary));

        TrainerTrainingSummary actualSummary = trainerTrainingSummaryService.getTrainerTrainingSummaryByUsername(username);

        assertEquals(expectedSummary, actualSummary);
        verify(trainerTrainingSummaryRepository, times(1)).findByUsername(username);
    }

    @Test
    public void getTrainerTrainingSummaryByUsername_NonExistingTrainer_ReturnsNull() {
        String username = "testUsername";
        when(trainerTrainingSummaryRepository.findByUsername(username)).thenReturn(Optional.empty());

        TrainerTrainingSummary actualSummary = trainerTrainingSummaryService.getTrainerTrainingSummaryByUsername(username);

        assertEquals(null, actualSummary);
        verify(trainerTrainingSummaryRepository, times(1)).findByUsername(username);
    }

    private TrainingEvent createTrainingEvent() {
        TrainingEvent event = new TrainingEvent();
        event.setUsername("testUsername");
        event.setFirstName("John");
        event.setLastName("Doe");
        event.setTrainingDate(LocalDate.now());
        event.setTrainingDuration(60);
        event.setTrainerStatus(true);
        return event;
    }

    private TrainerTrainingSummary eventToTrainerTrainingSummary(TrainingEvent event) {
        TrainerTrainingSummary summary = new TrainerTrainingSummary();
        summary.setUsername(event.getUsername());
        summary.setFirstName(event.getFirstName());
        summary.setLastName(event.getLastName());
        summary.setTrainerStatus(event.isTrainerStatus());
        summary.setYears(new ArrayList<>());
        return summary;
    }
}
