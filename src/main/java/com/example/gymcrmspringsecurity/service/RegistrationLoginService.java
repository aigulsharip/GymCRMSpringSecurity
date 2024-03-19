package com.example.gymcrmspringsecurity.service;

import com.example.gymcrmspringsecurity.entity.registration.TraineeRegistrationRequest;
import com.example.gymcrmspringsecurity.entity.registration.TraineeRegistrationResponse;
import com.example.gymcrmspringsecurity.entity.registration.TrainerRegistrationRequest;
import com.example.gymcrmspringsecurity.entity.registration.TrainerRegistrationResponse;
import com.example.gymcrmspringsecurity.entity.trainee.Trainee;
import com.example.gymcrmspringsecurity.entity.trainer.Trainer;
import com.example.gymcrmspringsecurity.repository.TraineeRepository;
import com.example.gymcrmspringsecurity.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
@Slf4j
public class RegistrationLoginService {
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public RegistrationLoginService(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public TraineeRegistrationResponse registerTrainee (TraineeRegistrationRequest traineeRegistrationRequest) {
        log.info("Registering trainee: {}", traineeRegistrationRequest);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Trainee trainee = modelMapper.map(traineeRegistrationRequest, Trainee.class);
        trainee.setUsername(calculateUsername(trainee.getFirstName(), trainee.getLastName()));
        trainee.setPassword(generatePassword());
        trainee.setIsActive(true);
        trainee = traineeRepository.save(trainee);
        log.info("Trainee registered successfully: {}", trainee);
        return modelMapper.map(trainee, TraineeRegistrationResponse.class);
    }

    public TrainerRegistrationResponse registerTrainer (TrainerRegistrationRequest trainerRegistrationRequest) {
        log.info("Registering trainer: {}", trainerRegistrationRequest);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Trainer trainer = modelMapper.map(trainerRegistrationRequest, Trainer.class);
        trainer.setUsername(calculateUsername(trainer.getFirstName(), trainer.getLastName()));
        trainer.setPassword(generatePassword());
        trainer.setIsActive(true);
        trainer = trainerRepository.save(trainer);
        log.info("Trainer registered successfully: {}", trainer);
        return modelMapper.map(trainer, TrainerRegistrationResponse.class);
    }

    public boolean isTrainer(String firstName, String lastName) {
        log.info("Checking if {} {} is a trainer", firstName, lastName);
        boolean result = trainerRepository.existsByFirstNameAndLastName(firstName, lastName);
        log.info("{} {} is{} a trainer", firstName, lastName, result ? "" : " not");
        return result;
    }

    public boolean isTrainee(String firstName, String lastName) {
        log.info("Checking if {} {} is a trainee", firstName, lastName);
        boolean result = traineeRepository.existsByFirstNameAndLastName(firstName, lastName);
        log.info("{} {} is{} a trainee", firstName, lastName, result ? "" : " not");
        return result;
    }

    private String calculateUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;
        String calculatedUsername = baseUsername.toLowerCase(Locale.ROOT);
        int counter = 1;

        while (traineeRepository.existsByUsername(calculatedUsername) || trainerRepository.existsByUsername(calculatedUsername)) {
            calculatedUsername = baseUsername + counter++;
        }
        return calculatedUsername.toLowerCase();
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
    /*
    // Register trainee logic without modelMapper:25 lines of code
    public TraineeRegistrationResponse registerTrainee(TraineeRegistrationRequest request) {
        // Create Trainee entity from request
        Trainee trainee = new Trainee();
        trainee.setFirstName(request.getFirstName());
        trainee.setLastName(request.getLastName());
        trainee.setDateOfBirth(request.getDateOfBirth());
        trainee.setAddress(request.getAddress());
        trainee.setIsActive(true);
        trainee.setPassword("password");
        trainee.setUsername("username");

        // Save trainee to the database
        trainee = traineeRepository.save(trainee);

        // Generate username and password
        String username = calculateUsername(trainee.getFirstName(), trainee.getLastName());
        String password = generatePassword();

        // Update trainee with username and password
        trainee.setUsername(username);
        trainee.setPassword(password);
        traineeRepository.save(trainee);

        // Create registration response
        return new TraineeRegistrationResponse(username, password);
    }

     */

}




