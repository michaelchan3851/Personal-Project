package com.integrated.demousermanagementsystem.exception;

import com.integrated.demousermanagementsystem.infra.Code;



public class UserNotFoundException extends UserException {

  public UserNotFoundException( Code code) {
    super( code);
  }

}
