package com.example.gymcrmspringsecurity.activemq.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOrder implements Serializable {
    private String bookId;
    private String  customerId;


}
