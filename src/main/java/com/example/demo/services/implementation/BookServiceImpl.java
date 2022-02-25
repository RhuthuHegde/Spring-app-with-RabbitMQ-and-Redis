package com.example.demo.services.implementation;

import com.example.demo.bookmodel.Books;
import com.example.demo.bookrepository.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@CacheConfig(cacheNames = "Books")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        System.out.println("Getting from the DB");
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(key="#id")
    public Books findByBookId(Long id) {
        System.out.println("Getting from the DB");
        Books book=bookRepository.findBooksByBookId(id);
        return book;
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
    @CachePut(key="#id")
    public Books updateBooks(Long id, Books book) {
        Books updated_book=bookRepository.findBooksByBookId(id);
        updated_book.setBookName(book.getBookName());
        bookRepository.save(updated_book);
        return updated_book;
    }

    @Override
    @CacheEvict(key="#id",allEntries = true)
    public void deleteBooks(Long id) {
        Books delete_book=bookRepository.findBooksByBookId(id);
        if(delete_book!=null)
        bookRepository.delete(delete_book);
    }
}
