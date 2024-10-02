package com.cartservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long productId;

    @Column
    private int quantity;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name = "cart")
    private Cart cart;
}
