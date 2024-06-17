package com.example.skabelon1backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "delivery")
    private List<ProductOrder> productOders;
}
