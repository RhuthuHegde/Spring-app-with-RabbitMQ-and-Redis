package com.example.demo.services.implementation;

import com.example.demo.bookmodel.Books;
import com.example.demo.bookrepository.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books findByBookId(Long id) {
        return bookRepository.findBooksByBookId(id);
    }


    @Override
    public Books saveBooks(Books book) {
        return bookRepository.save(book);
    }

    @Override
    public Long count() {
        return bookRepository.count();
    }

    @Override
    public Books updateBooks(Long id, Books book) {
        Books updated_book=bookRepository.findBooksByBookId(id);
//                .orElseThrow(
//                ()->new ResourceNotFoundException("Book id not found with id "+id));
        updated_book.setBookName(book.getBookName());
//        updated_book.setUsers(book.getUsers());
        bookRepository.save(updated_book);
        return updated_book;
    }

    @Override
    public void deleteBooks(Long id) {
        Books delete_book=bookRepository.findBooksByBookId(id);
//                      .orElseThrow(
//                ()->new ResourceNotFoundException("Book id not found with id "+id));
        bookRepository.delete(delete_book);
    }
}
