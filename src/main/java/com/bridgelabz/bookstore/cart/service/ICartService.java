package com.bridgelabz.bookstore.cart.service;

import com.bridgelabz.bookstore.cart.dto.CartDTO;
import com.bridgelabz.bookstore.cart.entity.CartData;

import java.util.List;

public interface ICartService {
    CartData addToCart(int userId, CartDTO cartDto);
   String deleteById(int cartid);


   CartData updateCart(int userId, int cartId, int qty);

    List<CartData> findAll();

    List<CartData> getCartDetailsByUserId(int userId);

    CartData changeCartQuantity(int userId, int cartId, CartDTO cartDto);

}
