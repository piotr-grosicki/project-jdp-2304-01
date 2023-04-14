package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductGroupRepositoryTests {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProductGroup() {
        //Given
        ProductGroup productGroup = new ProductGroup("Group1");

        //When
        productGroupRepository.save(productGroup);
        Optional<ProductGroup> foundEntity = productGroupRepository.findById(productGroup.getId());

        //Then
        assertTrue(foundEntity.isPresent());
        assertEquals(productGroup.getName(), foundEntity.get().getName());

        //CleanUp
        productGroupRepository.deleteById(productGroup.getId());
    }

    @Test
    public void testFindProductGroupList() {
        //Given
        ProductGroup productGroup1 = new ProductGroup("Group1");
        ProductGroup productGroup2 = new ProductGroup("Group2");

        //When
        productGroupRepository.save(productGroup1);
        productGroupRepository.save(productGroup2);
        List<ProductGroup> productGroupList = (List<ProductGroup>) productGroupRepository.findAll();

        //Then
        assertEquals(2, productGroupList.size());

        //CleanUp
        productGroupRepository.deleteAll();
    }

    @Test
    public void testFindProductGroupById() {
        //Given
        ProductGroup productGroup1 = new ProductGroup("Group1");
        ProductGroup productGroup2 = new ProductGroup("Group2");

        //When
        productGroupRepository.save(productGroup1);
        productGroupRepository.save(productGroup2);
        Optional<ProductGroup> foundEntity = productGroupRepository.findById(productGroup2.getId());

        //Then
        assertTrue(foundEntity.isPresent());
        assertEquals(productGroup2.getName(), foundEntity.get().getName());

        //CleanUp
        productGroupRepository.deleteAll();
    }

    @Test
    public void testSaveWithProduct_ProductsSaved() {
        //Given
        ProductGroup productGroup = new ProductGroup("Group1");
        Product product1 = new Product();
        Product product2 = new Product();
        productGroup.getProducts().add(product1);
        productGroup.getProducts().add(product2);

        product1.setProductGroup(productGroup);
        product2.setProductGroup(productGroup);

        //When
        productGroupRepository.save(productGroup);
        Optional<Product> foundEntity = productRepository.findById(product2.getId());

        //Then
        assertTrue(foundEntity.isPresent());
        assertEquals(product2.getId(), foundEntity.get().getId());

        //CleanUp
        productGroupRepository.deleteAll();
    }

    @Test
    public void testDeleteGroup_ProductsDeleted() {
        //Given
        ProductGroup productGroup = new ProductGroup("Group1");
        Product product1 = new Product();
        Product product2 = new Product();
        productGroup.getProducts().add(product1);
        productGroup.getProducts().add(product2);

        product1.setProductGroup(productGroup);
        product2.setProductGroup(productGroup);

        productGroupRepository.save(productGroup);

        //When
        productGroupRepository.deleteById(productGroup.getId());
        Optional<Product> foundEntity = productRepository.findById(product2.getId());

        //Then
        assertFalse(foundEntity.isPresent());

        //CleanUp
        productGroupRepository.deleteAll();
    }
}
