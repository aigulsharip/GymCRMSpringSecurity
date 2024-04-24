package com.example.gymcrmspringsecurity.rabbit.sender;

import dto.TrainingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingPublisher {
    private final RabbitTemplate rabbitTemplate;

    @Value("${mq.training.topic.exchange}")
    private String topicExchange;

    @Value("${mq.trainee.notification.topic.exchange}")
    private String traineeNotificationExchange;

    @Value("${mq.trainer.notification.topic.exchange}")
    private String trainerNotificationExchange;

    public void sendTraining(TrainingDTO trainingDTO, String trainingType){
        String routingKey = "training." + trainingType;
        rabbitTemplate.convertAndSend(topicExchange, routingKey, trainingDTO);
    }

    public void sendNotificationTrainingToTrainee(TrainingDTO trainingDTO, String trainingType){
        String routingKey = "trainee." + trainingType;
        rabbitTemplate.convertAndSend(traineeNotificationExchange, routingKey, trainingDTO);
    }

    public void sendNotificationTrainingToTrainer(TrainingDTO trainingDTO, String trainingType){
        String routingKey = "trainer." + trainingType;
        rabbitTemplate.convertAndSend(trainerNotificationExchange, routingKey, trainingDTO);

    }

}
