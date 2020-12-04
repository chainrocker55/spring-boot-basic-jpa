package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx
				= SpringApplication.run(BookStoreApplication.class, args);

		System.out.println(ctx.getBeanDefinitionCount());
	}

}
