package com.bridgelabz.bookstore.cart.entity;

import com.bridgelabz.bookstore.bookservice.entity.BookEntity;
import com.bridgelabz.bookstore.cart.dto.CartDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CartData")
public class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")

    private BookEntity book;
    @Column(name = "quantity")

    private int quantity;
    @Column(name = "total_price")

    private int totalPrice;



    public CartData(UserEntity user , BookEntity book, int cartPrice, CartDTO cartDto) {
        this.user = user;
        this.book = book;
        this.quantity = cartDto.getQuantity();
        this.totalPrice = cartPrice;
    }
}
