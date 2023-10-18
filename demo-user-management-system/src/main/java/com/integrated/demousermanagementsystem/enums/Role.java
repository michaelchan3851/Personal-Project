package com.integrated.demousermanagementsystem.enums;

import lombok.Getter;

@Getter
public enum Role {
  ADMIN("Admin"),
  NORMAL("Normal"),
  PRIORITY("Priority");

  private String desc;

  private Role(String desc) {
    this.desc = desc;
  }

  public static Role getRole(String role) {
    for (Role r : Role.values()) {
      if (r.getDesc().equals(role))
        return r;
    }
    return null;
  }
}
