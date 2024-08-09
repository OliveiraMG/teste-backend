package com.example.teste.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupSales {
    private String groupName;
    private Double sales;

    public GroupSales(String groupName, Double sales) {
        this.groupName = groupName;
        this.sales = sales;
    }
}
