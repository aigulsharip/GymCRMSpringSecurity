package com.example.gymcrmspringsecurity.rabbit.controller;


import com.example.gymcrmspringsecurity.rabbit.sender.MessageSender;
import com.example.gymcrmspringsecurity.rabbit.sender.TrainingPublisher;
import dto.TrainingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitController {

    private final MessageSender messageSender;

    private final TrainingPublisher trainingPublisher;


    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            messageSender.sendMessage(message);
            return new ResponseEntity<>("Message send to RabbitMQ: " + message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/training/publish")
    public ResponseEntity<String> createTraining(@RequestBody TrainingDTO trainingDTO) {
        try {
            trainingPublisher.sendTraining(trainingDTO, "fitness");
            return new ResponseEntity<>("Training created", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Training failed to create", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/training/notification/trainee")
    public ResponseEntity<String> notifyTrainee(@RequestBody TrainingDTO trainingDTO) {
        try {
            trainingPublisher.sendNotificationTrainingToTrainee(trainingDTO, "trainee");
            return new ResponseEntity<>("Training notification send to trainee", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Training notification failed to send to trainee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/training/notification/trainer")
    public ResponseEntity<String> notifyTrainer(@RequestBody TrainingDTO trainingDTO) {
        try {
            trainingPublisher.sendNotificationTrainingToTrainer(trainingDTO, "trainee");
            return new ResponseEntity<>("Training notification send to trainer", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Training notification failed to send to trainer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}