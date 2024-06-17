package com.example.skabelon1backend.repository;

import com.example.skabelon1backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findByNameContaining(String name);
}
