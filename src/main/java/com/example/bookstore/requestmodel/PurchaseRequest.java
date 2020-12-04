package com.example.bookstore.requestmodel;

public class PurchaseRequest {
    private Integer basketId;
    private Integer customerId;

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(Integer basketId) {
        this.basketId = basketId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
