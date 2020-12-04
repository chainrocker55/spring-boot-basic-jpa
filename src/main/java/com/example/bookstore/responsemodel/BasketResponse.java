package com.example.bookstore.responsemodel;

import com.example.bookstore.entiry.Book;
import com.example.bookstore.entiry.Customer;

import java.util.List;

public class BasketResponse {
    private int basketId;
    private List<Book> books;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
