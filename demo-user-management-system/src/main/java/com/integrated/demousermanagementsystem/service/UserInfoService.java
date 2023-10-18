package com.integrated.demousermanagementsystem.service;

import java.util.List;

import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;

public interface UserInfoService {

  List<UserDTO> findAll() throws UserException;

  void deleteById(Long id) throws UserException;

  UserDTO signUp(UserSignUpDTO SignUpUser) throws UserException;

  UserDTO signIn(String usernameOrEmail, String password);

  UserDTO resetPassword(String usernameOrEmail, String oldPassword, String newPassword) throws UserException;

  UserDTO getProfile(String username);

  UserInfo findUserByUsername(String username);

}
