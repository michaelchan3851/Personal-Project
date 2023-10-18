package com.integrated.demousermanagementsystem.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserSignUpDTO {

  private String username;
  private String password;
  private String name;
  private String gender;
  private String email;
  private String contact;
  private LocalDate dob;
  private String address;
  private LocalDateTime lastLogin;
  
}
