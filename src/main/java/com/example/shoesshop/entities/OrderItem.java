package com.example.shoesshop.entities;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "price")
    Double price;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "product_id")
    Long productid;

}
