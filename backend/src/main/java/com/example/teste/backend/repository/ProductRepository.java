package com.example.teste.backend.repository;

import com.example.teste.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByOrderByQuantitySoldAsc();
    List<Product> findByOrderByQuantitySoldDesc();
    List<Product> findByProductGroup(String group);
}
