package com.example.bookstore;

import com.example.bookstore.entiry.Book;
import com.example.bookstore.entiry.Customer;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BookStoreApplication {
	@Autowired
	private BookRepository repository;

	@Autowired
	private CustomerRepository customerRepository;
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx
				= SpringApplication.run(BookStoreApplication.class, args);

		System.out.println(ctx.getBeanDefinitionCount());
	}
	@PostConstruct
	public void initialDataForTesting() {
		for(int i = 1; i<=5 ; i++){
			Book book= new Book();
			book.setId(i);
			book.setBookName("book_"+i);
			book.setPrice(100);
			book.setNumBook(100);
			repository.save(book);
		}
		Customer customer = new Customer();
		customer.setFirstname("Kachain");
		customer.setLastname("Jantalat");
		customerRepository.save(customer);

	}

}
