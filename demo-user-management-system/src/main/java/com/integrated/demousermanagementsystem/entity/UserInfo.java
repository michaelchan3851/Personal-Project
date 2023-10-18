package com.integrated.demousermanagementsystem.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfo implements Serializable {

  @Id // primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "user_name")
  private String username;

  private String password;

  private String name;

  private String gender;

  private String email;

  private String contact;

  private String role;

  private LocalDate DOB;

  private String address;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

}
