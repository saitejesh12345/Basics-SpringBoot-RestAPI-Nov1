package com.example.bookManagement.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(int id) {
        super("Book does not exists for id: " + id);
    }
}
