package com.example.bookstore.controller;

import com.example.bookstore.entiry.Basket;
import com.example.bookstore.entiry.Book;
import com.example.bookstore.entiry.Customer;
import com.example.bookstore.repository.BasketRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.requestmodel.BasketRequest;
import com.example.bookstore.responsemodel.BasketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BasketController {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CustomerRepository customerRepository;



    @PostMapping("/v1/basket/build")
    public BasketResponse createBasket(@RequestBody BasketRequest basketRequest) {
        Basket basket = new Basket();
        //already created after project boot
        Optional<Customer> customer = customerRepository.findById(basketRequest.getCustomerId());
        if(customer.isPresent()){
            basket.setCustomer(customer.get());
            basketRepository.save(basket);

            BasketResponse response = new BasketResponse();
            response.setBasketId(basket.getId());
            response.setBooks(basket.getBooks());
            response.setCustomer(basket.getCustomer());

            return response;
        }

        throw new RuntimeException("Cannot create basket customer may not found");

    }
    @GetMapping("/v1/basket/{id}")
    public BasketResponse getBasketById(@PathVariable int id) {
        Optional<Basket> basket = basketRepository.findById(id);
        if (basket.isPresent()) {
            BasketResponse response = new BasketResponse();
            response.setBasketId(basket.get().getId());
            response.setBooks(basket.get().getBooks());
            response.setCustomer(basket.get().getCustomer());

            return response;
        }
        throw new RuntimeException("Basket not found with id=" + id);

    }
    @PutMapping("/v1/basket/add")
    public BasketResponse addBookToBasket(@RequestBody BasketRequest basketRequest) {
        Optional<Basket> basket = basketRepository.findById(basketRequest.getBasketId());
        if(basket.isPresent()){
            Basket basketResult = basket.get();
            Optional<Book> book = bookRepository.findById(basketRequest.getBookId());
            if(book.isPresent()){
                basketResult.getBooks().add(book.get());
                basketRepository.save(basketResult);
            }
            //return response
            BasketResponse response = new BasketResponse();
            response.setBasketId(basket.get().getId());
            response.setBooks(basketResult.getBooks());

            return response;
        }

        throw new RuntimeException("Basket not found with id [please call api create basket first]");

    }
}
