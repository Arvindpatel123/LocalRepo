package com.Airbnb.service;

import com.Airbnb.Dto.LoginDto;
import com.Airbnb.Dto.PropertyUserDto;
import com.Airbnb.entity.PropertyUser;
import com.Airbnb.reopsitory.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

     private PropertyUserRepository  UserRepository;

     private JWTService jwtService;


    public UserService(PropertyUserRepository propertyUserRepository,JWTService jwtService) {
        this.UserRepository = propertyUserRepository;
        this.jwtService=jwtService;
    }

    public PropertyUser addUser(PropertyUserDto propertyUserDto)
    {
        PropertyUser user=  new PropertyUser();
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setEmail(propertyUserDto.getEmail());
        user.setUserName(propertyUserDto.getUserName());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt()));
        user.setUserRole(propertyUserDto.getUserRole());
       PropertyUser savedUser= UserRepository.save(user);
       return  savedUser;

    }


    public String verifyLogin(LoginDto loginDto) {

      Optional<PropertyUser> opUser= UserRepository.findByUserName(loginDto.getUsername());
      if(opUser.isPresent())
      {
             PropertyUser propertyUser=opUser.get();
          if(BCrypt.checkpw(loginDto.getPassword(),propertyUser.getPassword()));
             return  jwtService.generateToken(propertyUser);
      }
        return null;
    }
}
