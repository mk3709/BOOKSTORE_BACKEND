package com.bridgelabz.bookstore.bookservice.repository;

import com.bridgelabz.bookstore.bookservice.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {

    
}
