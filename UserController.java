package com.Airbnb.controller;

import com.Airbnb.Dto.LoginDto;
import com.Airbnb.Dto.PropertyUserDto;
import com.Airbnb.entity.PropertyUser;
import com.Airbnb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto propertyUserDto)
    {
          PropertyUser propertyUser =  userService.addUser(propertyUserDto);
          if(propertyUser!=null)
          {
              return new ResponseEntity<>("Registration is succesfull", HttpStatus.CREATED);
          }
          return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
    {
               String token =  userService.verifyLogin(loginDto);
           if(token!=null){
               return new ResponseEntity<>(token,HttpStatus.OK);
           }
           return new ResponseEntity<>("invalid credentials",HttpStatus.UNAUTHORIZED);
    }

}
