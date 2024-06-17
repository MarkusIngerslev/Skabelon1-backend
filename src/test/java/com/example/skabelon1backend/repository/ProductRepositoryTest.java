package com.example.skabelon1backend.repository;

import com.example.skabelon1backend.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateProduct() {
        // Create a new product
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);
        product.setWeight(500);

        // Save the product to the database
        Product savedProduct =productRepository.save(product);

        // Check that the product was saved correctly
        assertThat(savedProduct.getId()).isNotNull();
    }
}
