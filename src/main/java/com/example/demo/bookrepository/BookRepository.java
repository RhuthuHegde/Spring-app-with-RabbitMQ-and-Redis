package com.example.demo.bookrepository;

import com.example.demo.bookmodel.Books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books,Number> {
  Books findBooksByBookId(Long id);
}
