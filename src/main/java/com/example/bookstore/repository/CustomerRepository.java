package com.example.bookstore.repository;

import com.example.bookstore.entiry.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
