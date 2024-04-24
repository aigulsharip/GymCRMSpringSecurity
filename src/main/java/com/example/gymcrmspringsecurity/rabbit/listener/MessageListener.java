package com.example.gymcrmspringsecurity.rabbit.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {
    /*

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(
                            value = "message-exchange",
                            type = ExchangeTypes.DIRECT),
                    value = @Queue(
                            value = "message-exchange"),
                    key = "key123"
            )
    )
    public void receiveMessage(String message) {
        System.out.println(message);
        log.info("Message received: {}", message);
    }

     */

}
