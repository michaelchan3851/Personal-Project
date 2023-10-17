package com.integrated.demousermanagementsystem.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import com.integrated.demousermanagementsystem.infra.ApiResp;
import com.integrated.demousermanagementsystem.infra.Code;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = UserException.class)
  @ResponseStatus(value = HttpStatus.OK)
  public ApiResp<Void> userExceptionHandler(UserException e) {
    return ApiResp.<Void>builder() //
        .status(Code.fromCode(e.getCode())) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  @ResponseStatus(value = HttpStatus.OK)
  public ApiResp<Void> userExceptionHandler(
      ConstraintViolationException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage()).data(null) //
        .data(null) //
        .build();
  }
  // ConstraintViolationException

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> runtimeExceptionHandler(RuntimeException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage()).data(null) //
        .build();
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> exceptionHandler(Exception e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage()).data(null) //
        .build();
  }

  private static Code getRespCode(Exception e) {
    if (e instanceof IllegalArgumentException) {
      return Code.IAE_EXCEPTION;
    } else if (e instanceof EntityNotFoundException) {
      return Code.ENTITY_NOT_FOUND;
    }
    // ...
    return null;
  }
}
