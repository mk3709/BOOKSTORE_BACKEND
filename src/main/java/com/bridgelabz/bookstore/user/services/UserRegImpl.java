package com.bridgelabz.bookstore.user.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.user.util.EmailSender;
import com.bridgelabz.bookstore.user.util.UserJwt;
import com.bridgelabz.bookstore.user.dto.UserLoginDto;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.repository.IBookStoreRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static java.time.LocalDate.parse;

@Service
public class UserRegImpl implements IUserReg {
    @Autowired
    private IBookStoreRepository userRepository;

    @Autowired
    UserJwt userJwt;



    @Autowired
    EmailSender emailSender;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String userRegistration(UserEntity userEntity){

        String encodePassword = passwordEncoder.encode(userEntity.getUserPassword());
        userEntity.setUserPassword(encodePassword);
        userEntity.setUserVerfied(false);

        userRepository.save(userEntity);
        String body = "Thanks for registering in Book store app."+ " click here to verify you account   " + "http://localhost:8085/login" ;
        String subject = "registered successfully and get your account verified";
        System.out.println(userEntity.getUserEmailId());
        emailSender.sendEmail(userEntity.getUserEmailId(), subject ,body);
        return  "User register successfully";
    }

    public String userLogin(UserLoginDto userLoginDto){
        UserEntity userEntity = userRepository.findByUserEmailId(userLoginDto.getUserEmailId());
        if(userEntity != null && passwordEncoder.matches(userLoginDto.getUserPassword(),userEntity.getUserPassword())){

            String token = userJwt.createToken(userEntity.getUserFirstName());
            if(userEntity.getUserVerfied().equals(false)) {
                String body = "Successfully registed";
                String subject = "account verified";
                emailSender.sendEmail(userLoginDto.getUserEmailId(), subject, body);
                userEntity.setUserVerfied(true);
                userRepository.save(userEntity);
            }
            return "Login successful. JWT Token: " + token;
        } else {
            return "Invalid credentials. login failed";
        }
    }
    @Override
    public List<UserEntity> getUserByJWT(String token){
        try {
            String userFirstName = userJwt.decodeToken(token);
            UserEntity userEntity = userRepository.findByFirstName(userFirstName);
            System.out.println(userEntity);
            return Collections.singletonList(userEntity);
        } catch (JWTVerificationException ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<UserEntity> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getById(int id) {
       return  userRepository.findById(id).stream().collect(Collectors.toList());
    }



 public  void sendOtp(String email)
 {
     String otp = generateOtp();
     String body=" Your Otp"+otp;
     String subject= "Reset Otp";
     emailSender.sendEmail(email,subject,body);

     UserEntity userEntity = userRepository.findByUserEmailId(email);
     if(userEntity!=null)
         userEntity.setOtp(otp);
     userRepository.save(userEntity);// otp send through emailSender



 }




    private boolean validateOtp(String email,String otp)
    {
        UserEntity userEntity = userRepository.findByUserEmailId(email);
        if(userEntity!=null && userEntity.getOtp()!=null && userEntity.getOtp().equals(otp))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
  public  String generateOtp()
  {
      Random random = new Random();
      int otpNumber=100000*random.nextInt(90000);
      return String.valueOf(otpNumber);//int to string
  }


    public void resetPassword(String email, String otp, String newPassword) {
        UserEntity userEntity = userRepository.findByUserEmailId(email);
        if (userEntity != null && validateOtp(email, otp)) {

            //new passwordGeneration encoding

            String encryptedPass = passwordEncoder.encode(newPassword);
            userEntity.setUserPassword(encryptedPass);


            //Check otp is sent
            userEntity.setOtp(null);
            userRepository.save(userEntity);
            String body = "Your password has been changed sucessfully";
            String subject = "Password Changed";
            emailSender.sendEmail(email, subject, body);
        } else {
            throw new RuntimeException("Invalid OTP or email address.");
        }


    }
}
