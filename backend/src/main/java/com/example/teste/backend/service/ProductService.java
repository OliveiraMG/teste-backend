package com.example.teste.backend.service;

import com.example.teste.backend.model.CMVData;
import com.example.teste.backend.model.GroupSales;
import com.example.teste.backend.model.Product;
import com.example.teste.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getLessSoldProducts() {
        return productRepository.findByOrderByQuantitySoldAsc();
    }

    public List<Product> getMostSoldProducts() {
        return productRepository.findByOrderByQuantitySoldDesc();
    }

    public List<GroupSales> get3GroupsMostSold() {
        List<Product> products = productRepository.findAll();
        Map<String, Double> salesByGroup = new HashMap<>();

        for (Product product : products) {
            String group = product.getProductGroup();
            double sales = product.getSalePrice() * product.getQuantitySold();
            salesByGroup.merge(group, sales, Double::sum);
        }

        List<GroupSales> groupSalesList = salesByGroup.entrySet().stream()
                .map(entry -> new GroupSales(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(GroupSales::getSales).reversed())
                .collect(Collectors.toList());

        return groupSalesList.stream().limit(3).collect(Collectors.toList());
    }
    public Product saveProduct(Product product) {
        String normalizedProductName = normalizeString(product.getName());
        String normalizedProductGroup = normalizeString(product.getProductGroup());

        List<Product> matchingProducts = productRepository.findByProductGroup(normalizedProductGroup)
                .stream()
                .filter(p -> normalizeString(p.getName()).equals(normalizedProductName))
                .collect(Collectors.toList());

        if (!matchingProducts.isEmpty()) {
            Product existingProduct = matchingProducts.stream()
                    .max(Comparator.comparing(Product::getLastSaleDate))
                    .orElse(null);

            if (existingProduct != null) {
                existingProduct.setQuantitySold(existingProduct.getQuantitySold() + product.getQuantitySold());
                if (product.getLastSaleDate().after(existingProduct.getLastSaleDate())) {
                    existingProduct.setLastSaleDate(product.getLastSaleDate());
                }
                existingProduct.setSalePrice(product.getSalePrice());
                return productRepository.save(existingProduct);
            }
        }

        product.setName(normalizeString(product.getName()));
        product.setProductGroup(normalizeString(product.getProductGroup()));

        return productRepository.save(product);
    }


    public List<CMVData> getCMVData() {
        List<Product> products = productRepository.findAll();
        Map<YearMonth, Double> cmvPerMonth = new TreeMap<>();

        for (Product product : products) {
            YearMonth yearMonth = YearMonth.from(product.getLastSaleDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            cmvPerMonth.merge(yearMonth, product.getSalePrice() * product.getQuantitySold(), Double::sum);
        }

        return cmvPerMonth.entrySet().stream()
                .map(entry -> new CMVData(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase()
                .trim();
    }
}