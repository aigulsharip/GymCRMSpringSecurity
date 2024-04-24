package com.example.gymcrmspringsecurity.activemq;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activemq")
public class ActiveMqController {

    private final Sender messageSender;

    private final Receiver receiver;

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            messageSender.sendMessage("order-queue", message);
            return new ResponseEntity<>("Message send to ActiveMQ: " + message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listen")
    public ResponseEntity<String> listenMessage(String message) {
        try {
            receiver.receiveMessage( message);
            return new ResponseEntity<>("Message received ActiveMQ: " + message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}