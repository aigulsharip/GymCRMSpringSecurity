package com.example.gymcrmspringsecurity.activemq.jms_service;

import com.example.gymcrmspringsecurity.activemq.pojos.BookOrder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class WarehouseReceiver {

    @JmsListener(destination = "book.order.queue")
    public void receive(BookOrder bookOrder){
        System.out.println("Order Received = " + bookOrder);
    }
}