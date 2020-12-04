package com.example.bookstore.entiry;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "customer_id")
    private int id;
    private String name;
    private String lastname;


//    @OneToOne(mappedBy = "customer")
//    private Basket basket;
//
//    public Basket getBasket() {
//        return basket;
//    }
//
//    public void setBasket(Basket basket) {
//        this.basket = basket;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

