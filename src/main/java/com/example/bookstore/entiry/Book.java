package com.example.bookstore.entiry;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;
    private String bookName;
    private Integer price;
    private int numBook;

//    @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private List<Basket> baskets = new ArrayList<>();
//
//    public List<Basket> getBaskets() {
//        return baskets;
//    }
//
//    public void setBaskets(List<Basket> baskets) {
//        this.baskets = baskets;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getNumBook() {
        return numBook;
    }

    public void setNumBook(int numbook) {
        this.numBook = numbook;
    }
}
