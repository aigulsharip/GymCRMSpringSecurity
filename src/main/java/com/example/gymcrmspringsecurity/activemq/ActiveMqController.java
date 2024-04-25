package com.example.gymcrmspringsecurity.activemq;


import com.example.gymcrmspringsecurity.activemq.jms_service.BookOrderService;
import com.example.gymcrmspringsecurity.activemq.jms_service.Sender;
import com.example.gymcrmspringsecurity.activemq.pojos.BookOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activemq")
public class ActiveMqController {

    private final Sender messageSender;


    @Autowired
    private BookOrderService bookOrderService;


    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            messageSender.sendMessage("order-queue", message);
            return new ResponseEntity<>("Message send to ActiveMQ: " + message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/sendBook")
    public ResponseEntity<String> sendBookOrder(@RequestBody BookOrder bookOrder) {
        try {
            bookOrderService.send(bookOrder);
            System.out.println("Order sent to warehouse for bookId = " + bookOrder.getBookId() + " from customerId = " + bookOrder.getCustomerId() + " successfully processed!");
            return new ResponseEntity<>("Message send to ActiveMQ: " + bookOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*

    @GetMapping("/order/{customerId}/{bookId}/")
    public ResponseEntity<String> processOrder(@PathVariable("customerId") String customerId,
                                             @PathVariable("bookId") String bookId )  {
        System.out.println("check endpoint");
        try {
            BookOrder bookOrder = new BookOrder();
            bookOrder.setBookOrderId(bookId);
            bookOrder.setCustomerId(customerId);
            bookOrderService.send(bookOrder);
            System.out.println("Order sent to warehouse for bookId = " + bookId + " from customerId = " + customerId + " successfully processed!");
            return new ResponseEntity<>("Message send to ActiveMQ: " + bookOrder, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Failed to send message: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

     */




}