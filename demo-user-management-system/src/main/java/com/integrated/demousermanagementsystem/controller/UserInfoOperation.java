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
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;

public interface UserInfoOperation {

  @GetMapping(value = "/users")
  @ResponseStatus(value = HttpStatus.OK)
  List<UserDTO> findAll();

  @PostMapping(value = "/signin")
  @ResponseStatus(value = HttpStatus.OK)
  UserDTO signIn(@RequestParam String username, @RequestParam String password);

  @DeleteMapping(value = "/user/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  void deleteById(@PathVariable Long id);

  @PostMapping("/signup")
  @ResponseStatus(value = HttpStatus.CREATED)
  void signUp(@RequestBody UserSignUpDTO userSignUpDTO);

  @PatchMapping("/reset/password")
  @ResponseStatus(HttpStatus.OK)
  UserDTO resetPassword(@RequestParam String usernameOrEmail, 
                        @RequestParam String oldPassword, 
                        @RequestParam String newPassword);
}
