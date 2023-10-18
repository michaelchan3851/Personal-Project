package com.integrated.demousermanagementsystem.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrated.demousermanagementsystem.controller.UserInfoOperation;
import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.infra.ApiResp;
import com.integrated.demousermanagementsystem.infra.Code;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.service.impl.UserInfoServiceImp;

@RestController
@RequestMapping(value = "/api/v1")
public class UserInfoController implements UserInfoOperation {

  @Autowired
  UserInfoServiceImp userInfoServiceImp;

  @Override
  public ApiResp<List<UserDTO>> findAll() throws UserException{
      List<UserDTO> userDTOs = userInfoServiceImp.findAll();
      return ApiResp.<List<UserDTO>>builder().ok().data(userDTOs).build();

  }

  @Override
  public UserDTO signIn(String username, String password) {
    return userInfoServiceImp.signIn(username, password);
  }

  @Override
  public void deleteById(Long id) throws UserException{
    userInfoServiceImp.deleteById(id);
  }

  @Override
  public UserDTO signUp(UserSignUpDTO userSignUpDTO) {
    return userInfoServiceImp.signUp(userSignUpDTO);
  }

  @Override
  public UserDTO resetPassword(String usernameOrEmail, String oldPassword, String newPassword) {
    return userInfoServiceImp.resetPassword(usernameOrEmail, oldPassword, newPassword);
  }

  @Override
  public ApiResp<UserDTO> getProfile(String username){
    return ApiResp.<UserDTO> builder().ok().data(userInfoServiceImp.getProfile(username)).build();
  }

}
