package com.example.bookstore.responsemodel;

public class BookResponse {
    private int bookId;
    private String bookName;
    private int price;
    private int numBook;

    public int getNumBook() {
        return numBook;
    }

    public void setNumBook(int numBook) {
        this.numBook = numBook;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
