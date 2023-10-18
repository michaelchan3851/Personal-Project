package com.integrated.demousermanagementsystem.infra;

import lombok.Getter;

@Getter
public enum Code {
  OK(20000, "OK"),
  // 90000 - 99999
  IAE_EXCEPTION(90000, "Illegal Argument Exception."),
  ALREADY_EXIST(90001,"Entity Already Existed"),
  ENTITY_NOT_FOUND(90002,"Entity Not Found"),
  VALIDATOR_FAILURE(90003,"Incorrect Username/Password"),
  USER_NOT_FOUND(90004, "User Not Found")  // New entry for User Not Found error
  ;

  private int code;
  private String desc;

  private Code(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static Code fromCode(int code) {
    for (Code c : Code.values()) {
      if (c.code == code) {
        return c;
      }
    }
    throw new IllegalArgumentException();
  }

}
