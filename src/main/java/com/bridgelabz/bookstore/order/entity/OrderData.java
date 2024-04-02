package com.bridgelabz.bookstore.order.entity;


import com.bridgelabz.bookstore.bookservice.entity.BookEntity;
import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orderData")
public class OrderData{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate orderDate;
    private int quantity;
    private String address;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity book;
    private boolean cancel = false;
    private int orderPrice;


    public OrderData(UserEntity user, BookEntity book,int orderPrice, OrderDTO orderDto) {
        this.user = user;
        this.book = book;
        this.orderDate = LocalDate.now();
        this.quantity = orderDto.getQuantity();
        this.address = orderDto.getAddress();
        this.orderPrice = orderPrice;
    }
}