package com.example.gymcrmspringsecurity.activemq;


import com.example.gymcrmspringsecurity.activemq.jms_service.TrainingInfoSenderService;
import com.example.gymcrmspringsecurity.activemq.jms_service.simple.Sender;
import dto.TrainingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activemq")
public class ActiveMqController {

    private final Sender messageSender;


    @Autowired
    private TrainingInfoSenderService trainingInfoSenderService;


    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            messageSender.sendMessage("order-queue", message);
            return new ResponseEntity<>("Message send to ActiveMQ: " + message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/sendTrainingInfo")
    public ResponseEntity<String> sendTrainingInfo(@RequestBody TrainingInfo trainingInfo) {
        try {
            trainingInfoSenderService.send(trainingInfo);
            System.out.println("Created training send to CRM system for trainer = " + trainingInfo.getTrainerName() + " for trainee = " + trainingInfo.getTraineeName() + " successfully processed!");
            return new ResponseEntity<>("Message send to ActiveMQ: " + trainingInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}