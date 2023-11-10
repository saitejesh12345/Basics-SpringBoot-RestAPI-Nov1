package com.example.bookManagement.service;

import com.example.bookManagement.entity.Book;
import com.example.bookManagement.exception.BookAlreadyExistException;
import com.example.bookManagement.exception.BookNotFoundException;
import com.example.bookManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public Boolean addBook(Book book) throws BookAlreadyExistException{
        Optional<Book> bookOpt=bookRepository.getById(book.getBookId()); //bookId alredy Exists
        if(bookOpt.isPresent()){
            throw new BookAlreadyExistException(book.getBookId());
        }
            return bookRepository.add(book);
        //return true;
    }

    public Book getBookById(int id) throws BookNotFoundException{
        Optional<Book> bookOptional =  bookRepository.getById(id);
        if(bookOptional.isEmpty()){
            throw  new BookNotFoundException(id);
        }
        return bookOptional.get();
    }

    //change to 1,harry potter I ,JK Rowling, 700
    public String  updateBook(int id, String title, String author, Integer pages) {

    try {
        Book book1 = getBookById(id); //1,harry potter ,JK Rowling, 500
        if(title!=null) {
            book1.setTitle(title);
       }
        if(author!=null) {
            book1.setAuthor(author);
        }
       if(pages!=null) {
            book1.setPages(pages);
       }

        bookRepository.add(book1);
        return "book updated";
    }catch (BookNotFoundException ex){
        Book book = new Book(id,title,author,pages);
        bookRepository.add(book);
        return "Book Created";
    }


    }

    public Boolean deleteBook(int id) throws  BookNotFoundException{

       Optional<Book> bookOpt = bookRepository.getById(id);
        if(bookOpt.isEmpty()){
            throw new BookNotFoundException(id);
        }
        bookRepository.removeById(id);
        return true;
    }
}
