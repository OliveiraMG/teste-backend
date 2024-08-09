package com.example.teste.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String productGroup;
    private Double salePrice;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date lastSaleDate;
    private Integer quantitySold;
}
