package com.example.gymcrmspringsecurity.entity.trainingType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingTypeResponse {
    private Long id;
    private String trainingTypeName;
}
