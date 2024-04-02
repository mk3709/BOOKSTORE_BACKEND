package com.bridgelabz.bookstore.cart.service;


import com.bridgelabz.bookstore.bookservice.entity.BookEntity;
import com.bridgelabz.bookstore.bookservice.repository.BookRepository;
import com.bridgelabz.bookstore.bookservice.service.BookServiceImp;
import com.bridgelabz.bookstore.cart.entity.CartData;
import com.bridgelabz.bookstore.cart.dto.CartDTO;

import com.bridgelabz.bookstore.cart.entity.CartData;

import com.bridgelabz.bookstore.cart.repository.CartRepository;

import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.repository.IBookStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    BookServiceImp iBookService;
    @Autowired
    private IBookStoreRepository iBookStoreRepository;


    @Override
    public CartData addToCart(int userId, CartDTO cartDto) {

        UserEntity user = iBookStoreRepository.findById(userId).orElse(null);
        BookEntity book = iBookService.getId(cartDto.getBookId());

        if(user != null && book != null){

            int cartPrice = book.getBookPrice() * cartDto.getQuantity();

            CartData cart = new CartData(user,book,cartPrice,cartDto);

            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public String deleteById(int cartid) {
        Optional<CartData> cart = cartRepository.findById(cartid);
        if(cart != null) {
            cartRepository.deleteById(cartid);
            return "Cart Removed";
        }
        else {
            try {
                throw  new Exception("Cart cannot be null");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        }

    @Override
    public CartData changeCartQuantity(int userId, int cartId, CartDTO cartDto) {

        UserEntity user = iBookStoreRepository.findById(userId).orElse(null);
        BookEntity book = bookRepository.findById(cartDto.getBookId()).orElse(null);
        CartData cart = cartRepository.findById(cartId).orElse(null);

        if(cart != null && user != null){

            if(book != null){
                cart.setBook(book);
                cart.setQuantity(cartDto.getQuantity());
                cart.setTotalPrice(book.getBookPrice() * cartDto.getQuantity());
                return cartRepository.save(cart);
            }

        }

        return null;
    }
    @Override
    public CartData updateCart(int userId, int cartId, int qty) {
        UserEntity user = iBookStoreRepository.findById(userId).orElse(null);
        CartData cart = cartRepository.findById(cartId).orElse(null);
        cart.setQuantity(qty);
        return null;
    }

    @Override
    public List<CartData> findAll() {

        List<CartData> cartList = cartRepository.findAll();
        return cartList;

    }

    @Override
    public List<CartData> getCartDetailsByUserId(int userId) {

        List<CartData> userCartList = cartRepository.getCartListByUserId(userId);
        if(userCartList.isEmpty()){
            return null;
        }else
            return userCartList;
    }

}