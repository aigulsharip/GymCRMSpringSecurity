package com.example.gymcrmspringsecurity.mongodb.controller;
import com.example.gymcrmspringsecurity.mongodb.dto.TrainerTrainingSummary;
import com.example.gymcrmspringsecurity.mongodb.dto.TrainingEvent;
import com.example.gymcrmspringsecurity.mongodb.service.TrainerTrainingSummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerTrainingSummaryControllerTest {

    @Mock
    private TrainerTrainingSummaryService trainerTrainingSummaryService;

    @InjectMocks
    private TrainerTrainingSummaryController trainerTrainingSummaryController;

    @Test
    public void handleEvent_Success() {
        TrainingEvent event = new TrainingEvent();
        event.setUsername("testuser");

        ResponseEntity<String> responseEntity = trainerTrainingSummaryController.handleEvent(event);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Training event created successfully for username: testuser", responseEntity.getBody());
        verify(trainerTrainingSummaryService, times(1)).processEvent(event);
    }

    @Test
    public void getTrainerTrainingSummaryByUsername_ExistingUsername_Success() {
        String username = "testuser";
        TrainerTrainingSummary trainerTrainingSummary = new TrainerTrainingSummary();
        when(trainerTrainingSummaryService.getTrainerTrainingSummaryByUsername(username)).thenReturn(trainerTrainingSummary);

        ResponseEntity<TrainerTrainingSummary> responseEntity = trainerTrainingSummaryController.getTrainerTrainingSummaryByUsername(username);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trainerTrainingSummary, responseEntity.getBody());
        verify(trainerTrainingSummaryService, times(1)).getTrainerTrainingSummaryByUsername(username);
    }

    @Test
    public void getTrainerTrainingSummaryByUsername_NonExistingUsername_NotFound() {
        String username = "nonexistinguser";
        when(trainerTrainingSummaryService.getTrainerTrainingSummaryByUsername(username)).thenReturn(null);

        ResponseEntity<TrainerTrainingSummary> responseEntity = trainerTrainingSummaryController.getTrainerTrainingSummaryByUsername(username);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(trainerTrainingSummaryService, times(1)).getTrainerTrainingSummaryByUsername(username);
    }

    @Test
    public void getAllTrainerTrainingSummaries_Success() {
        List<TrainerTrainingSummary> summaries = new ArrayList<>();
        summaries.add(new TrainerTrainingSummary());
        when(trainerTrainingSummaryService.getAllTrainerTrainingSummaries()).thenReturn(summaries);

        ResponseEntity<List<TrainerTrainingSummary>> responseEntity = trainerTrainingSummaryController.getAllTrainerTrainingSummaries();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(summaries, responseEntity.getBody());
        verify(trainerTrainingSummaryService, times(1)).getAllTrainerTrainingSummaries();
    }
}
