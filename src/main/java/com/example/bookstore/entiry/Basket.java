package com.example.bookstore.entiry;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private int id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "book_basket",
            joinColumns = {@JoinColumn(name = "basket_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> books = new ArrayList<>();


//    @OneToOne(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
//    private Purchase purchase;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
//    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Purchase getPurchase() {
//        return purchase;
//    }
//
//    public void setPurchase(Purchase purchase) {
//        this.purchase = purchase;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}

