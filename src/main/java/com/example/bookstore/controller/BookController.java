package com.example.bookstore.controller;

import com.example.bookstore.entiry.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.responsemodel.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/v1/book/{id}")
    public BookResponse getBookById(@PathVariable int id) {

        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setBookId(book.get().getId());
            bookResponse.setBookName(book.get().getBookName());
            bookResponse.setPrice(book.get().getPrice());
            bookResponse.setNumBook(book.get().getNumBook());
            return bookResponse;
        }
        throw new RuntimeException("Book not found with id=" + id);

    }

    @GetMapping("/v1/books")
    public List<Book> getBooks() {

        Iterable<Book> book = bookRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        book.forEach(bookList::add);
        return bookList;

    }


}