package com.bridgelabz.bookstore.user.controller;

import com.bridgelabz.bookstore.user.dto.ForgetPasswordDTO;
import com.bridgelabz.bookstore.user.dto.ResetPasswordDTO;
import com.bridgelabz.bookstore.user.util.EmailSender;
import com.bridgelabz.bookstore.user.util.UserJwt;
import com.bridgelabz.bookstore.user.dto.UserLoginDto;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.services.IUserReg;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserReg userService;

    @Autowired
    UserJwt userJwt;

    @Autowired
    EmailSender emailSender;


    @PostMapping("/reg")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegistration(@RequestBody UserEntity userEntity){
        return userService.userRegistration(userEntity);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody UserLoginDto userLoginDto){
        return userService.userLogin(userLoginDto);
    }

    @GetMapping("/getByToken")
    public List<UserEntity> getByJwt(@RequestHeader String token){
        return userService.getUserByJWT(token);
    }

//    @GetMapping("/getAllUser")
//    public List<UserEntity> getALLUser()
//    {
//        return  userService.getAllUser();
//    }
//    @GetMapping("/getById/{id}")
//    public  List<UserEntity> getById(@PathVariable int id)
//    {
//        return userService.getById(id);
//    }



    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDto) {

        String email = forgetPasswordDto.getEmail();
        userService.sendOtp(email);
        return new ResponseEntity<>("OTP sent to email for password reset.",HttpStatus.CREATED);

    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {

        String email =resetPasswordDTO.getUserEmail();
        String otp =resetPasswordDTO.getOtp();
        String newPassword = resetPasswordDTO.getUserPassword();
        userService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok("Password reset successfully.");
    }


}
