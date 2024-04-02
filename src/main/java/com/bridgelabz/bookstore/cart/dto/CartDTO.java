package com.bridgelabz.bookstore.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private int userId;
    private int bookId;
    @NotNull(message = "quantity should not be null")
    private int quantity;

}