package com.example.bookstore.responsemodel;

import com.example.bookstore.entiry.Basket;

public class CustomerResponse {
    private int id;
    private String fullName;
    private Basket basket;

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name, String lastname) {
        this.fullName = name+" "+lastname;
    }
}
