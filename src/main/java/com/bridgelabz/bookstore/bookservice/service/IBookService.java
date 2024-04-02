package com.bridgelabz.bookstore.bookservice.service;

import com.bridgelabz.bookstore.bookservice.entity.BookEntity;

import java.util.List;

public interface IBookService {
    BookEntity getId(int id);

    List<BookEntity> getAllBooks();

    BookEntity updateBook(BookEntity book);


    void deleteBook(int id);

  void updateBookQuantity(int id , BookEntity quantityDTO);


  void updateBookPrice(int id, BookEntity priceDTO);

}
