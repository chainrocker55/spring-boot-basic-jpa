package com.example.bookstore.entiry;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "customer_id")
    private int id;
    private String firstname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

