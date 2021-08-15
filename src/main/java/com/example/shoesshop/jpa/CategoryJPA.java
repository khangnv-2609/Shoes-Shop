package com.example.shoesshop.jpa;

import com.example.shoesshop.entities.Categories;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryJPA extends PagingAndSortingRepository<Categories,Long> {
}
