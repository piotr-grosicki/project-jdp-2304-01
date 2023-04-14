package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.ProductGroupNotFoundException;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductGroupDbService {

    private final ProductGroupRepository repository;

    public ProductGroup saveProductGroup(final ProductGroup productGroup) {
        return repository.save(productGroup);
    }

    public ProductGroup getProductGroup(Long id) throws ProductGroupNotFoundException {
        return repository.findById(id).orElseThrow(ProductGroupNotFoundException::new);
    }

    public List<ProductGroup> getAllProductGroups() {
        return repository.findAll();
    }
}
