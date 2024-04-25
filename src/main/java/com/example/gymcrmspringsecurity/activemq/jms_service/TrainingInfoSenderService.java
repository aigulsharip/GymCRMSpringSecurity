package com.example.gymcrmspringsecurity.activemq.jms_service;

import dto.TrainingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class TrainingInfoSenderService {

    private static final String TRAINING_QUEUE = "training.info.active.queue";

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MessageConverter jacksonJmsMessageConverter;


    public void send(TrainingInfo trainingInfo){
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        jmsTemplate.convertAndSend(TRAINING_QUEUE, trainingInfo, message -> {
            message.setStringProperty("_type", TrainingInfo.class.getName());
            return message;
        });
    }
}
