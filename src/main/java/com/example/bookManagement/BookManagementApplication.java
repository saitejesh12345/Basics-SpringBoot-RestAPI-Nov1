package com.example.bookManagement;


import com.example.bookManagement.entity.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementApplication.class, args);
//		Map<Integer, Book> data = new HashMap<>();
//
//		Book book1 = new Book(123,"BookManagement","saiteja",295);
//		data.put(book1.getBookId(),book1);

	}


}
