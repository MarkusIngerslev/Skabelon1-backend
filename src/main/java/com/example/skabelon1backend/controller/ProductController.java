package com.example.skabelon1backend.controller;

import com.example.skabelon1backend.entity.Product;
import com.example.skabelon1backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Get methods for products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Post method for creating a product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Put method for updating a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setWeight(productDetails.getWeight());
            return ResponseEntity.ok(productRepository.save(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete method for deleting a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
