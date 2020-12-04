package com.example.bookstore.controller;

import com.example.bookstore.entiry.Basket;
import com.example.bookstore.entiry.Purchase;
import com.example.bookstore.repository.BasketRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.PurchaseRepository;
import com.example.bookstore.requestmodel.PurchaseRequest;
import com.example.bookstore.responsemodel.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PurchaseController {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/v1/order/{id}")
    public PurchaseResponse getOrderById(@PathVariable int id) {

        Optional<Purchase> order = purchaseRepository.findById(id);
        if (order.isPresent()) {
            PurchaseResponse response = new PurchaseResponse();
            response.setPurchaseId(order.get().getId());
            response.setBasket(order.get().getBasket());
            response.setDiscount(order.get().getDiscount());
            response.setNetPrice(order.get().getNetPrice());
            response.setTotalPrice(order.get().getTotalPrice());

            return response;
        }
        throw new RuntimeException("Order not found with id=" + id);

    }
    @PostMapping("/v1/order/create")
    public PurchaseResponse createOrder(@RequestBody PurchaseRequest purchaseRequest) {
        Purchase purchase = new Purchase();
        Optional<Basket> basket = basketRepository.findById(purchaseRequest.getBasketId());
        if(basket.isPresent()){
            purchase.setBasket(basket.get());
            purchase.setNetPrice(80.0);
            purchase.setDiscount(20.0);
            purchase.setTotalPrice(100.0);

            purchaseRepository.save(purchase);
        }

        PurchaseResponse response = new PurchaseResponse();
        response.setPurchaseId(purchase.getId());
        response.setBasket(purchase.getBasket());
        response.setDiscount(purchase.getDiscount());
        response.setNetPrice(purchase.getNetPrice());
        response.setTotalPrice(purchase.getTotalPrice());

        return response;

    }
}

