package com.example.bookstore.entiry;

import com.example.bookstore.repository.BasketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BasketTest {
    @Autowired
    private BasketRepository basketRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void check_sql_statement_when_persisting_in_many_to_many_correct() {
        Book book = new Book();
        book.setId(1);
        book.setBookName("book1");
        book.setNumBook(100);
        book.setPrice(100);

        Book book2 = new Book();
        book2.setId(2);
        book2.setBookName("book2");
        book2.setNumBook(100);
        book2.setPrice(100);

        Basket basket = new Basket();
        basket.setId(1);
        basket.setBooks(Arrays.asList(book, book2));
        //entityManager.persist(basket);
        basketRepository.save(basket);
        Optional<Basket> result = basketRepository.findById(1);
        assertNotNull(result.get());
    }
}