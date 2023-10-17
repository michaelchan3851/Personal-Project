package com.integrated.demousermanagementsystem.exception;

import com.integrated.demousermanagementsystem.infra.BusinessException;
import com.integrated.demousermanagementsystem.infra.Code;

public class UserException extends BusinessException {

  public UserException(Code code) {
    super(code);
  }

}
