package com.example.demo.controllers;

import com.example.demo.bookmodel.Books;
import com.example.demo.rabbitconfiguration.MessageConfiguration;
import com.example.demo.services.BookService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RabbitTemplate template;

    @GetMapping({"/books"})
    public ResponseEntity<List<Books>> getbooks()
    {
        List<Books> listBooks=bookService.getAllBooks();
        return new ResponseEntity<>(listBooks, HttpStatus.OK);
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity<String> getBooksbyId(@PathVariable Long bookId)
    {
        Books book=bookService.findByBookId(bookId);
        return new ResponseEntity<>("The book is\n "+book,HttpStatus.OK);
    }
    @GetMapping("/books/count")
    public ResponseEntity<String> count()
    {
        Long val=bookService.count();
        return new ResponseEntity<>("The number of books are "+val, HttpStatus.OK);
    }
    @PostMapping("/books/add")
    public ResponseEntity<String> addBooks(@RequestBody Books book)
    {
        Books bookAdded=bookService.saveBooks(book);
        template.convertAndSend(MessageConfiguration.EXCHANGE,MessageConfiguration.ROUTING_KEY1,bookAdded);
        return new ResponseEntity<>("The book is added with the id "+bookAdded.getBookId(),HttpStatus.CREATED);
    }
    @PutMapping("/books/update/{bookId}")
    public ResponseEntity<String> changeBooks(@PathVariable Long bookId, @RequestBody Books book)
    {
        Books bookChanged= bookService.updateBooks(bookId,book);
        template.convertAndSend(MessageConfiguration.EXCHANGE,MessageConfiguration.ROUTING_KEY1,bookChanged);
        return new ResponseEntity<>("The book with following id is updated with new values "+bookId+"\n"+bookChanged,HttpStatus.OK);
    }
    @DeleteMapping("/books/delete/{bookId}")
    public ResponseEntity<String> deleteBooks(@PathVariable Long bookId)
    {
        bookService.deleteBooks(bookId);
        return new ResponseEntity<>("The book with following ID has been deleted: "+bookId,HttpStatus.OK);
    }


}
