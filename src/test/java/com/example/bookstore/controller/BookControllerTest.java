package com.example.bookstore.controller;

import com.example.bookstore.entiry.Book;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookControllerTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void check_sql_statement_when_persisting_in_many_to_many_correct() {
        Book book = new Book();
        book.setId(1);
        book.setBookName("book1");
        book.setNumBook(100);
        book.setPrice(100);


        bookRepository.save(book);
        Optional<Book> result = bookRepository.findById(1);

        assertNotNull(result.get());
    }

}