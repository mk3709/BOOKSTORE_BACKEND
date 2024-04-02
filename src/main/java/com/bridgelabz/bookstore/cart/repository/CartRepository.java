package com.bridgelabz.bookstore.cart.repository;


import com.bridgelabz.bookstore.cart.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartData,Integer> {

    @Query(value = "select * from cart_data where user_id=:id", nativeQuery = true)
    List<CartData> getCartListByUserId(int id);
}