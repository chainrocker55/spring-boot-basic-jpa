package com.example.bookstore.repository;

import com.example.bookstore.entiry.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {
}
