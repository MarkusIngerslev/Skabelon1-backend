package com.example.skabelon1backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fromWarehouse;
    private String destination;
    private double totalPrice;
    private double totalWeight;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductOrder> productOrders;


    // Helper method to add ProductOrder to Delivery
    public void addProductOrder(ProductOrder productOrder) {
        productOrders.add(productOrder);
        productOrder.setDelivery(this);
    }

    // Helper method to remove ProductOrder from Delivery
    public void removeProductOrder(ProductOrder productOrder) {
        productOrders.remove(productOrder);
        productOrder.setDelivery(null);
    }
}
