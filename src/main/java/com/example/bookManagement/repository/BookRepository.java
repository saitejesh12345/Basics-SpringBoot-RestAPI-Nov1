package com.example.bookManagement.repository;

import com.example.bookManagement.entity.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookRepository {

    Map<Integer, Book> data = new HashMap<>(); //temp database

    public Boolean add(Book book) {
        data.put(book.getBookId(),book);
        return  true;
    }

    public Optional<Book> getById(int id) {
            if(data.containsKey(id)){
                return Optional.of(data.get(id));
            }
        return Optional.empty();
    }

    public Boolean removeById(int id) {
        data.remove(id);
        return true;
    }

//    public void updateBook(Book book) {
//        data.put(book.getBookId(),book);
//    }
}
