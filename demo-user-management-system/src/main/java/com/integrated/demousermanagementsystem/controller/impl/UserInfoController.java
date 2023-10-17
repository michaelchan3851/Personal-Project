package com.integrated.demousermanagementsystem.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrated.demousermanagementsystem.controller.UserInfoOperation;
import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.service.impl.UserInfoServiceImp;

@RestController
@RequestMapping(value = "/api/v1")
public class UserInfoController implements UserInfoOperation {

  @Autowired
  UserInfoServiceImp userInfoServiceImp;

  public List<UserDTO> findAll() {
    return userInfoServiceImp.findAll();
  }

  public UserDTO signIn(String username, String password) {
    return userInfoServiceImp.signIn(username, password);
  }

  public void deleteById(Long id) {
    userInfoServiceImp.deleteById(id);
  }

  public void signUp(UserSignUpDTO userSignUpDTO) {
    userInfoServiceImp.signUp(userSignUpDTO);
  }

  public UserDTO resetPassword(String usernameOrEmail, String oldPassword, String newPassword) {
    return userInfoServiceImp.resetPassword(usernameOrEmail, oldPassword, newPassword);
  }
}
