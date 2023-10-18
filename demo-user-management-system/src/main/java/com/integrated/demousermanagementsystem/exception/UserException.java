package com.integrated.demousermanagementsystem.exception;

import com.integrated.demousermanagementsystem.infra.Code;

public class UserException extends Exception {

  private int code;

  public UserException(Code code) {
    super(code.getDesc());
    this.code = code.getCode();
  }

  public int getCode() {
    return code;
  }

}
