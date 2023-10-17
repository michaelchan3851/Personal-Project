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
public class UserDTO {
  private Long id;
  private String name;
  private Character gender;
  private String email;
  private String phoneNumber;
  private String role;
  private LocalDate DOB;
  private String address;
  private LocalDateTime lastLogin;

}
