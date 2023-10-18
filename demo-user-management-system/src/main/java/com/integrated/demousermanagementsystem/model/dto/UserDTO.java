package com.integrated.demousermanagementsystem.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class UserDTO {
  private Long id;
  private String name;
  private String gender;
  private String email;
  private String contact;
  private String role;
  private LocalDate dob;
  private String address;
  private LocalDateTime lastLogin;

}
