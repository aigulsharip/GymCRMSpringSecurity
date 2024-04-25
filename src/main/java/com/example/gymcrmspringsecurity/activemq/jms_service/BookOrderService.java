package com.example.gymcrmspringsecurity.activemq.jms_service;

import com.example.gymcrmspringsecurity.activemq.pojos.BookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class BookOrderService {

    private static final String BOOK_QUEUE = "book.order.queue";

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MessageConverter jacksonJmsMessageConverter;


    public void send(BookOrder bookOrder){
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder, message -> {
            message.setStringProperty("_type", BookOrder.class.getName());
            return message;
        });
    }
}
