package com.example.gymcrmspringsecurity.entity.trainer;

import com.example.gymcrmspringsecurity.entity.trainingType.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerInfo {
    private String username;
    private String firstName;
    private String lastName;
    private TrainingType trainingType;

}
