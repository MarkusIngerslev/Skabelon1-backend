package com.example.skabelon1backend.controller;

import com.example.skabelon1backend.entity.Delivery;
import com.example.skabelon1backend.entity.ProductOrder;
import com.example.skabelon1backend.repository.DeliveryRepository;
import com.example.skabelon1backend.repository.ProductOrderRepository;
import com.example.skabelon1backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get all deliveries
    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    // Get a delivery by id
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable int id)  {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a delivery
    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    // Add a Product order to a delivery
    @PostMapping("/{deliveryId}/productOrders")
    public ResponseEntity<Delivery> addProductOrderToDelivery(@PathVariable int deliveryId, @RequestBody ProductOrder productOrder) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            productOrder.setDelivery(delivery);
            productOrderRepository.save(productOrder);
            delivery.addProductOrder(productOrder);
            return ResponseEntity.ok(deliveryRepository.save(delivery));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
