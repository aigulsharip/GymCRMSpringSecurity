package com.example.gymcrmspringsecurity.rabbit.controller;


import com.example.gymcrmspringsecurity.rabbit.listener.MessageListener;
import com.example.gymcrmspringsecurity.rabbit.sender.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitController {

    private final MessageSender messageSender;

    private final MessageListener messageListener;

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            messageSender.sendMessage(message);
            return new ResponseEntity<>("Message send to RabbitMQ: " + message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listen")
    public ResponseEntity<String> receiveMessage(String message) {
        try {
            messageListener.receiveMessage("Custom message");
            return new ResponseEntity<>("Message received from RabbitMQ: " + "Custom" + message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}