package com.example.teste.backend.controller;

import com.example.teste.backend.model.CMVData;
import com.example.teste.backend.model.GroupSales;
import com.example.teste.backend.model.Product;
import com.example.teste.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/least-sold")
    public List<Product> getLessSoldProducts() {
        return productService.getLessSoldProducts();
    }

    @GetMapping("/most-sold")
    public List<Product> getMostSoldProducts() {
        return productService.getMostSoldProducts();
    }

    @GetMapping("/groups-most-sold")
    public ResponseEntity<List<GroupSales>> getGroupsMostSold() {
        List<GroupSales> groupsMostSold = productService.get3GroupsMostSold();
        return ResponseEntity.ok(groupsMostSold);
    }

    @GetMapping("/cmv")
    public ResponseEntity<List<CMVData>> getCMVData() {
        List<CMVData> cmvDataList = productService.getCMVData();
        return ResponseEntity.ok(cmvDataList);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productSave = productService.saveProduct(product);
        return ResponseEntity.ok(productSave);
    }

}
