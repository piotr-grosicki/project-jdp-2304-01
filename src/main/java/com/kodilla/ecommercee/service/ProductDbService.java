package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product getProductById(final Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void removeProduct(final Long productId) {
        productRepository.deleteById(productId);
    }
}
