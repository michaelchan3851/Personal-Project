package com.integrated.demousermanagementsystem.service;

import java.util.List;

import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;

public interface UserInfoService {
  
  List<UserDTO> findAll() ;

  void deleteById(Long id);

  void signUp(UserSignUpDTO userSignUpDTO);

  UserDTO signIn(String usernameOrEmail, String password);

  UserDTO resetPassword(String usernameOrEmail, String oldPassword, String newPassword);
}
