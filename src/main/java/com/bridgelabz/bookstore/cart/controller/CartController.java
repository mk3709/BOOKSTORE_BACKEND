package com.bridgelabz.bookstore.cart.controller;


import com.bridgelabz.bookstore.cart.dto.CartDTO;
import com.bridgelabz.bookstore.cart.entity.CartData;
import com.bridgelabz.bookstore.cart.service.ICartService;
import com.bridgelabz.bookstore.user.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService iCartService;


    @PostMapping("/addToCart/{userId}")
    public ResponseEntity<ResponseDTO> addToCart(@PathVariable int userId, @RequestBody CartDTO cartDto){
        CartData cart = iCartService.addToCart(userId,cartDto);
        ResponseDTO responseDto = new ResponseDTO("Added to cart",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int cartId) {

        iCartService.deleteById(cartId);
        ResponseDTO reponseDTO = new ResponseDTO("cart Data deleted ", "deleted id " + cartId);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);

    }


    @PostMapping("/updateQuantity/{userId}/{cartId}")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@PathVariable int userId, @PathVariable int cartId, @RequestBody CartDTO cartDto) {

        CartData cart = iCartService.changeCartQuantity(userId,cartId,cartDto);
        ResponseDTO responseDTO = new ResponseDTO("Cart Quantity Updated", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/updateQty/{userId}/{cartId}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int userId, @PathVariable int cartId, @RequestBody int qty){

        CartData cart = iCartService.updateCart(userId, cartId, qty);
        ResponseDTO responseDTO = new ResponseDTO("Cart Quantity changed", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> findAllDetails() {

        List<CartData> cartList = iCartService.findAll();
        ResponseDTO responseDTO = new ResponseDTO("All CartList", cartList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @GetMapping("/CartByUser/{userId}")
    public ResponseEntity<ResponseDTO> getCartDataByUserID(@PathVariable int userId){

        List<CartData> userCartDetails = iCartService.getCartDetailsByUserId(userId);
        ResponseDTO responseDTO = new ResponseDTO("Cart Details by id", userCartDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}