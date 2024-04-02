package com.bridgelabz.bookstore.user.services;

import com.bridgelabz.bookstore.user.dto.UserLoginDto;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import jakarta.mail.MessagingException;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserReg {

    String userRegistration(UserEntity userEntity);

    String userLogin(UserLoginDto userLoginDto);

    List<UserEntity> getUserByJWT(String token);

    List<UserEntity> getAllUser();
    List<UserEntity> getById(int id);
//    String setPassword(String email,String newPassword);
    public  void sendOtp(String email);

    void resetPassword(String email, String otp, String newPassword);
}



