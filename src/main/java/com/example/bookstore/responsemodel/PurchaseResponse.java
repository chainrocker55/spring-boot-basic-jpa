package com.example.bookstore.responsemodel;

import com.example.bookstore.entiry.Basket;

public class PurchaseResponse {
    private int purchaseId;
    private Basket basket;
    private double totalPrice;
    private double netPrice;
    private double discount;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket bucket) {
        this.basket = bucket;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
