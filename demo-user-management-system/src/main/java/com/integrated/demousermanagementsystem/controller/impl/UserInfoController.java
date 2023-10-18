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
import com.integrated.demousermanagementsystem.service.impl.UserInfoServiceImpl;

@RestController
@RequestMapping(value = "/api/v1")
public class UserInfoController implements UserInfoOperation {

  @Autowired
  UserInfoServiceImpl userInfoService;

  @Override
  public ApiResp<List<UserDTO>> findAll() throws UserException {
    List<UserDTO> userDTOs = userInfoService.findAll();
    return ApiResp.<List<UserDTO>>builder().ok().data(userDTOs).build();

  }

  @Override
  public UserDTO signIn(String username, String password) {
    return userInfoService.signIn(username, password);
  }

  @Override
  public void deleteById(Long id) throws UserException {
    userInfoService.deleteById(id);
  }

  @Override
  public ApiResp<UserDTO> signUp(UserSignUpDTO userSignUpDTO) throws UserException {
    return ApiResp.<UserDTO>builder().ok().data(userInfoService.signUp(userSignUpDTO)).build();
  }

  @Override
  public ApiResp<UserDTO> resetPassword(String usernameOrEmail, String oldPassword, String newPassword)
      throws UserException {
    return ApiResp.<UserDTO>builder() //
        .ok() //
        .data(userInfoService.resetPassword(usernameOrEmail, oldPassword, newPassword)) //
        .build();//
  }

  @Override
  public ApiResp<UserDTO> getProfile(String username) {
    return ApiResp.<UserDTO>builder().ok().data(userInfoService.getProfile(username)).build();
  }

}
