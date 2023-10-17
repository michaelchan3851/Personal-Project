package com.integrated.demousermanagementsystem.infra;

import lombok.Getter;

@Getter
public enum Code {
  OK(20000, "OK"),
  // 40000 - 49999
  NOTFOUND(40000, "Resource NOT FOUND."), //
  // Server
  THIRD_PARTY_SERVER_UNAVAILABLE(50001, "Third Party Service Unavailable."), //
  // RuntimeException: 90000 - 99999
  IAE_EXCEPTION(90000, "Illegal Argument Exception."), //
  ENTITY_NOT_FOUND(90001, "Entity Not Found."), //
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
