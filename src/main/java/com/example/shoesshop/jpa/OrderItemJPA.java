package com.example.shoesshop.jpa;

import com.example.shoesshop.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;


public interface OrderItemJPA extends CrudRepository<OrderItem,Long> {
}
