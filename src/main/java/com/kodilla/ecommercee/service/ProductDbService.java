package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.ProductGroupNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteProductById(final Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}
