package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO implements Serializable {
    private String traineeName;
    private String trainerName;
    private String trainingType;
    private String traineeEmail;
    private String trainerEmail;
    private LocalDate trainingDate;
    private int trainingDuration;

}
