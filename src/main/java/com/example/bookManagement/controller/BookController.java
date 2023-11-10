package com.example.bookManagement.controller;

import com.example.bookManagement.entity.Book;
import com.example.bookManagement.exception.BookAlreadyExistException;
import com.example.bookManagement.exception.BookNotFoundException;
import com.example.bookManagement.repository.BookRepository;
import com.example.bookManagement.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.Objects;

@RestController
@RequestMapping("/BookManagement")
public class BookController {
  //  map id --> book we can use map for storage Database

    @Autowired
    private BookService bookService;


    @PostMapping("/add-Book")
    public ResponseEntity addBook(@RequestBody Book book){ //json-format
        try {
            Boolean added = bookService.addBook(book);
            return new ResponseEntity("Added Successfully", HttpStatus.CREATED);
        }catch (BookAlreadyExistException ex){
           return new ResponseEntity("Unable to add book as it Already exists.",HttpStatus.valueOf(400));
        }
    }

        @GetMapping("/findbook/{id}") //   findbook/1
     public ResponseEntity getBookById(@PathVariable("id") int id) {
            try {
               Book book = bookService.getBookById(id);
               return new ResponseEntity(book,HttpStatus.OK);
            } catch (BookNotFoundException ex){
            return new ResponseEntity("Book not found",HttpStatus.valueOf(500));
            }catch (Exception ex){
                return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
            }
     }



    @PutMapping("/updated-book/{id}")
     public ResponseEntity updateBook(@PathVariable int id,@RequestParam String title,
                             @RequestParam(required = false) String author,@RequestParam Integer pages){
       try {
           String response = bookService.updateBook(id, title, author, pages);
           return new ResponseEntity<>(response, HttpStatus.valueOf(200));
       }
       catch (Exception ex){
         return  new ResponseEntity("exception occured",HttpStatus.valueOf(500)) ;
       }

    }

    @DeleteMapping("/remove-book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity("book removed successfully",HttpStatus.OK) ;
        }catch (BookNotFoundException ex){
            return  new ResponseEntity("Book not found",HttpStatus.NOT_FOUND);
        }
    }


//    @PostMapping("/addBook")
//    public Book addBooktoShelf(@RequestBody Book book){ //json-format
//        data.put(book.getBookId(),book);
//        //System.out.println(data.get(book.getBookId()));
//        return book;
//    }
//    @PostMapping("/add-fill-Book")
//    public String addBookByFilling(@RequestParam int bookId,@RequestParam String title,
//                                   @RequestParam String author, @RequestParam int pages){ //json-format
//        Book book = new Book(bookId,title,author,pages);
//        data.put(book.getBookId(),book);
//        //System.out.println(data.get(book.getBookId()));
//        return "Book with id: " + book.getBookId()+" "+"added successfully";
//    }

//    @GetMapping("/find-book/{id}") //    ? id = 1
//    public Book findBook(@RequestParam int id){
//      //  if(data.containsKey(id))
//        return  data.get(id);
//
//    }



//    @GetMapping("/findAllBooks")
//        public List<Book> getAllBooks(){
//            return data.values().stream().toList();
//        }

    //if i dont want to update author name we can keep optional
    //and we can keep if condition.
//    @PutMapping("/updated-book")
//    public String updateBook(@PathVariable int id,@RequestParam String title,
//                             @RequestParam(required = false) String author,@RequestParam int pages){
//        Book book = data.get(id);
//        if(author!= null){
//            book.setAuthor(author);
//        }
//        book.setTitle(title);
//        book.setPages(pages);
//        data.put(id,book);
//        return "book updated";
//    }


//    @PutMapping("/updated-book")
//    public String updateBookMarkDeafult(@RequestParam int id,@RequestParam(defaultValue = "default",required = false) String title,
//                             @RequestParam String author,@RequestParam int pages){
//        Book book = data.get(id);
//        if(Objects.nonNull(title)){
//            book.setTitle(title);
//        }

//        book.setAuthor(author);
//        book.setPages(pages);
//        data.put(id,book);
//        return "book updated";
//    }


//    @PutMapping("/updated-book")
//    public String updateBookMark(@RequestParam int id,@RequestParam   String title,
//                             @RequestParam String author,@RequestParam int pages){
//        Book book = data.get(id);
//        book.setTitle(title);
//        book.setAuthor(author);
//        book.setPages(pages);
//        data.put(id,book);
//        return "book updated";
//    }


//    @DeleteMapping("/remove-book/{id}")
//    public String  deleteBook(@PathVariable int id){
//        data.remove(id);
//       return "Book deleted Succesfull";
//    }
}
