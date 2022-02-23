package com.example.demo.services;
import com.example.demo.bookmodel.Books;

import java.util.List;

public interface BookService {

    List<Books> getAllBooks();
    Books findByBookId(Long id);
    Books saveBooks(Books book);
    Long count();
    Books updateBooks(Long id,Books book);
    void deleteBooks(Long id);
}
