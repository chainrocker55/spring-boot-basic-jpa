package com.example.bookstore.controller;

import com.example.bookstore.entiry.Customer;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.responsemodel.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/v1/customer/{id}")
    public CustomerResponse getCustomerById(@PathVariable int id) {

        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(customer.get().getId());
            customerResponse.setFullName(customer.get().getFirstname(), customer.get().getLastname());
            //customerResponse.setBasket(customer.get().getBasket());
            return customerResponse;
        }
        throw new RuntimeException("Customer not found with id=" + id);

    }
}
