package com.example.gymcrmspringsecurity.entity.trainer;

import com.example.gymcrmspringsecurity.entity.trainee.TraineeInfo;
import com.example.gymcrmspringsecurity.entity.trainingType.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerProfileResponse {
    private String firstName;
    private String lastName;
    private TrainingType specialization;
    private boolean isActive;
    private List<TraineeInfo> trainees;

}

