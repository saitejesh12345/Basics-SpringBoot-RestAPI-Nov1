package com.example.bookManagement.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    private  int bookId;

    private  String  title;

    private String  author;

    private int pages;
}
