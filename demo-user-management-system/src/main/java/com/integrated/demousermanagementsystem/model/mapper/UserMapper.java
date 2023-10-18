package com.integrated.demousermanagementsystem.model.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.enums.Role;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;

@Component
public class UserMapper {

  @Autowired
  private ModelMapper modelMapper;

  public UserInfo map(UserSignUpDTO userSignUpDTO) {
    return UserInfo.builder()
        .username(userSignUpDTO.getUsername()) //
        .password(userSignUpDTO.getPassword()) //
        .name(userSignUpDTO.getName()) //
        .gender(userSignUpDTO.getGender()) //
        .email(userSignUpDTO.getEmail()) //
        .contact(userSignUpDTO.getContact()) //
        .DOB(userSignUpDTO.getDOB()) //
        .address(userSignUpDTO.getAddress()) //
        .role(Role.NORMAL.getDesc()) //
        .lastLogin(LocalDateTime.now()) //
        .build();
  }

  // public UserDTO map(UserInfo userInfo){
  //   UserDTO dto = modelMapper.map(userInfo,UserDTO.class);
  //   return dto;
  // }

  public UserDTO map(UserInfo userInfo){
    return UserDTO.builder()
      .id(userInfo.getId()) //
      .name(userInfo.getName()) //
      .gender(userInfo.getGender()) //
      .email(userInfo.getEmail()) //
      .contact(userInfo.getContact())
      .role(userInfo.getRole()) //
      .DOB(userInfo.getDOB()) //
      .address(userInfo.getAddress()) //
      .lastLogin(userInfo.getLastLogin()) //
      .build();
  } 
}
