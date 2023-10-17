package com.integrated.demousermanagementsystem.model.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;

@Component
public class UserMapper {

  @Autowired
  private ModelMapper modelMapper;
  
  public  UserInfo map(UserSignUpDTO userSignUpDTO){
    return UserInfo.builder()
      .username(userSignUpDTO.getUsername()) //
      .password(userSignUpDTO.getPassword()) //
      .name(userSignUpDTO.getName()) //
      .gender(userSignUpDTO.getGender()) //
      .email(userSignUpDTO.getEmail()) //
      .phoneNumber(userSignUpDTO.getPhoneNumber()) //
      .DOB(userSignUpDTO.getDOB()) //
      .address(userSignUpDTO.getAddress()) //
      .lastLogin(LocalDateTime.now()) //
      .build();
  } 

  public UserDTO map(UserInfo userInfo){
    return UserDTO.builder()
      .id(userInfo.getId()) //
      .name(userInfo.getName()) //
      .gender(userInfo.getGender()) //
      .email(userInfo.getEmail()) //
      .phoneNumber(userInfo.getPhoneNumber())
      .role(userInfo.getRole()) //
      .DOB(userInfo.getDOB()) //
      .address(userInfo.getAddress()) //
      .lastLogin(userInfo.getLastLogin()) //
      .build();
  } 
}

