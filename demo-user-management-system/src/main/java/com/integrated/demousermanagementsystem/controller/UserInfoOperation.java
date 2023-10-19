package com.integrated.demousermanagementsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.infra.ApiResp;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;

import jakarta.persistence.EntityNotFoundException;

public interface UserInfoOperation {

  @GetMapping(value = "/users")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<List<UserDTO>> findAll() throws UserException;

  @PostMapping(value = "/signin")
  @ResponseStatus(value = HttpStatus.CREATED)
  UserDTO signIn(@RequestParam String username, @RequestParam String password);

  /**
   * For Users to delete their Account
   * 
   * @param user
   * @throws UserException
   */
  @DeleteMapping(value = "/user/delete/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  void deleteById(@PathVariable Long id)throws UserException;

  @PostMapping("/signup")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<UserDTO> signUp(@RequestBody UserSignUpDTO userSignUpDTO)throws UserException;
   

  @PatchMapping("/reset/password")
  @ResponseStatus(HttpStatus.OK)
  ApiResp<UserDTO> resetPassword(@RequestParam String usernameOrEmail,
      @RequestParam String oldPassword,
      @RequestParam String newPassword) throws UserException;
     
  /**
   * Request for the data in the UserProfile (UserDTO)
   * 
   * @param username
   * @return (onSuccess) Data of Profile without user password
   * @return (onFail) Api Error Response
   */
  @GetMapping(value = "/profile")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<UserDTO> getProfile(@RequestParam String username) throws EntityNotFoundException;

}
