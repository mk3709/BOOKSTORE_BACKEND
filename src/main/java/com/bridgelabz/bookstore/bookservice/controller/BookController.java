package com.bridgelabz.bookstore.bookservice.controller;


import com.bridgelabz.bookstore.bookservice.entity.BookEntity;
import com.bridgelabz.bookstore.bookservice.service.BookServiceImp;
import com.bridgelabz.bookstore.user.dto.UserLoginDto;
import com.bridgelabz.bookstore.user.util.EmailSender;
import com.bridgelabz.bookstore.user.util.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/books")
public class BookController {

    @Autowired
    BookServiceImp bookServiceImp;

    @Autowired
    UserJwt userJwt;

    @Autowired
    EmailSender emailSender;

//        @GetMapping("/getBooksALL")
//        public  List<BookEntity> getAllBooks()
//        {
//            return bookServiceImp.getAllBooks();
//        }


    @PostMapping("createbook")
    public BookEntity createBook(@RequestBody BookEntity bookEntity)
    {
      return bookServiceImp.createBook(bookEntity);
    }

    @GetMapping("/getId/{id}")
    public BookEntity getId(@PathVariable int  id)
    {
        return  bookServiceImp.getId(id);
    }

    @PutMapping("/update")
    public BookEntity updateBook(@RequestBody BookEntity book)
    {
        return bookServiceImp.updateBook(book);
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam int id)
    {
        bookServiceImp.deleteBook(id);
    }

    @PutMapping("/bookQuantity/{id}")
    public void updateBookQuantity(@PathVariable int id , @RequestBody BookEntity quantityDTO)
    {
         bookServiceImp.updateBookQuantity(id,quantityDTO);
    }

    @PutMapping("/bookPrice/{id}")
    public void updateBookPrice(@PathVariable int id,@RequestBody BookEntity priceDTO)
    {
       bookServiceImp.updateBookPrice(id,priceDTO);
    }





}
