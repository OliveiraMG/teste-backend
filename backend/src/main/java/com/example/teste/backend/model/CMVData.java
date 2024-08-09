package com.example.teste.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter
public class CMVData {
    private int year;
    private int month;
    private double value;

    public CMVData(YearMonth yearMonth, double value) {
        this.year = yearMonth.getYear();
        this.month = yearMonth.getMonthValue();
        this.value = value;
    }
}
