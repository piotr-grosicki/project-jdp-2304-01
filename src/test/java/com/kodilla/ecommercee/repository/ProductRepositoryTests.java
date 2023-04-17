package com.kodilla.ecommercee.repository;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductGroup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductGroupRepository groupRepository;

    @Test
    public void testProductSave() {
        //Given
        ProductGroup group = new ProductGroup("NAME");
        Product product1 = new Product();
        product1.setName("name");
        product1.setDescription("desc");
        product1.setPrice(66.6);
        product1.setProductGroup(group);
        group.getProducts().add(product1);
        groupRepository.save(group);

        //When
        long id = product1.getId();
        long groupId = group.getId();

        Optional<Product> readProduct = productRepository.findById(id);

        //Then
        assertTrue(readProduct.isPresent() && readProduct.get().getId() == id);

        //CleanUp
        groupRepository.deleteById(groupId);
    }

    @Test
    public void testFindAllProducts() {
        // Given
        ProductGroup group = new ProductGroup("NAME");
        Product product1 = new Product(1L, "Name", "Descr", 10.5, group, new ArrayList<>());
        Product product2 = new Product(2L, "Name2", "Descr2", 11.5, group, new ArrayList<>());
        Product product3 = new Product(3L, "Name3", "Descr3", 12.5, group, new ArrayList<>());

        // When
        groupRepository.save(group);
        List<Product> productList = Arrays.asList(product1, product2, product3);
        productRepository.saveAll(productList);
        List<Product> products = (List<Product>) productRepository.findAll();
        int productAmount = products.size();

        // Then
        long groupId = group.getId();
        assertEquals(3, productAmount);

        // CleanUp
        productRepository.deleteAll(productList);
        groupRepository.deleteById(groupId);
    }

    @Test
    public void testDeleteProduct_ShouldSaveGroup() {
        // Given
        ProductGroup group = new ProductGroup(null, "Group");
        Product product = new Product(null, "Product", "Description", 10.0, group, new ArrayList<>());
        groupRepository.save(group);
        productRepository.save(product);

        // When
        productRepository.deleteById(product.getId());

        // Then
        assertFalse(productRepository.findById(product.getId()).isPresent());
        assertTrue(groupRepository.findById(group.getId()).isPresent());


        //CleanUp
        groupRepository.deleteById(group.getId());
    }

}
