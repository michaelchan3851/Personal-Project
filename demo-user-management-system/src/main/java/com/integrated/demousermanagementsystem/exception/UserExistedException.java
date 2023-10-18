package com.integrated.demousermanagementsystem.exception;

import com.integrated.demousermanagementsystem.infra.Code;

public class UserExistedException extends UserException {

  public UserExistedException(Code code) {
    super(code);
  }

}