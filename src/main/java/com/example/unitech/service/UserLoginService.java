package com.example.unitech.service;
import com.example.unitech.dto.request.create.UserCreateRequest;
import com.example.unitech.dto.request.login.UserLoginRequest;
import com.example.unitech.dto.response.login.UserLoginResponse;
import com.example.unitech.entity.User;
import com.example.unitech.exception.UserNotFoundException;
import com.example.unitech.mapper.UserMapper;
import com.example.unitech.repository.UserRepository;
import com.example.unitech.service.create.UserCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserLoginService {
   private final UserRepository userRepository;
   private final UserMapper userMapper;
   private final EmailService emailService;

   public ResponseEntity<?> login(UserLoginRequest userLoginRequest){

      User user=userRepository.findByPin(userLoginRequest.getPin());
      if(user==null || !user.getPassword().equals(userLoginRequest.getPassword())){
         throw new UserNotFoundException(HttpStatus.NOT_FOUND.name(),"Invalid username or password");
      }
      UserLoginResponse response=userMapper.entityToLoginResponse(user);
      return  ResponseEntity.ok(response);
   }


//       public String forgetPassword(String email){
//        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
//        if(user.isPresent()){
//            emailService.forgetPassword(user.get().getEmail(),"abcg",user.get().getPassword());
//            return "success";
//        }//error msj
//        return "Bad request" ;
//    }


}
//mymsjhelper