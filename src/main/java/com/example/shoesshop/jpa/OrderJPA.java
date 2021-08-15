package com.example.shoesshop.jpa;

import com.example.shoesshop.entities.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderJPA extends PagingAndSortingRepository<Order,Long> {
}
