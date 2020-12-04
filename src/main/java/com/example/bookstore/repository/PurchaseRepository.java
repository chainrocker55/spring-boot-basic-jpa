package com.example.bookstore.repository;

import com.example.bookstore.entiry.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
