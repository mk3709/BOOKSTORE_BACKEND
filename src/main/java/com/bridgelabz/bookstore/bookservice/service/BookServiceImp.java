package com.bridgelabz.bookstore.bookservice.service;

import com.bridgelabz.bookstore.bookservice.config.Emailsender;
import com.bridgelabz.bookstore.bookservice.entity.BookEntity;
import com.bridgelabz.bookstore.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements IBookService {
    @Autowired
    BookRepository bookRepository;


    @Autowired
    Emailsender emailSender;


    public BookEntity createBook(BookEntity bookEntity)
    {
       bookRepository.save(bookEntity);
       return bookEntity;

    }
    @Override
    public  BookEntity getId(int id) {

     return bookRepository.findById(id).get();

    }

    @Override
    public List<BookEntity> getAllBooks() {

        return bookRepository.findAll();
    }

    @Override
    public BookEntity updateBook(BookEntity book) {
        bookRepository.findAll().stream().collect(Collectors.toList());
        bookRepository.save(book);
        return book;
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBookQuantity(@RequestBody int id , @RequestBody BookEntity quantityDTO) {
        bookRepository.findById(id);
        quantityDTO.setBookQuantity(quantityDTO.getBookQuantity());
       bookRepository.findById(id).stream().collect(Collectors.toList());
       bookRepository.save(quantityDTO);

    }

    @Override
    public void updateBookPrice(@RequestBody int id,@RequestBody BookEntity priceDTO) {

        bookRepository.findById(id);
        priceDTO.setBookPrice(priceDTO.getBookPrice());
      bookRepository.findById(id).stream().collect(Collectors.toList());
      bookRepository.save(priceDTO);
    }


}
