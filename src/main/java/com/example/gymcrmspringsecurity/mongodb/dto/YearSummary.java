package com.example.gymcrmspringsecurity.mongodb.dto;

import lombok.Data;

import java.util.List;

@Data
class YearSummary {
    private int year;
    private List<MonthSummary> months;
}
