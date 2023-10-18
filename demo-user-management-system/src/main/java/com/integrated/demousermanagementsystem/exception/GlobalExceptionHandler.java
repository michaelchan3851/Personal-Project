package com.integrated.demousermanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.integrated.demousermanagementsystem.infra.ApiResp;
import com.integrated.demousermanagementsystem.infra.Code;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserExistedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResp<Void> userExistedHandler(UserExistedException e) {
        return ApiResp.<Void>builder()
                .status(getRespCode(e))
                .data(null)
                .build();
    }

    @ExceptionHandler(value = UserNotFoundException.class) // Handle UserNotFoundException
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // Set HTTP status to 404 Not Found
    public ApiResp<Void> userNotFoundHandler(UserNotFoundException e) {
        return ApiResp.<Void>builder()
                .status(getRespCode(e))
                .data(null)
                .build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResp<Void> runtimeExceptionHandler(RuntimeException e) {
        return ApiResp.<Void>builder()
                .status(getRespCode(e))
                .concatMessageIfPresent(e.getMessage()).data(null)
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResp<Void> exceptionHandler(Exception e) {
        return ApiResp.<Void>builder()
                .status(getRespCode(e))
                .concatMessageIfPresent(e.getMessage()).data(null)
                .build();
    }

    private static Code getRespCode(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return Code.IAE_EXCEPTION;
        } else if (e instanceof UserExistedException) {
            return Code.ALREADY_EXIST;
        } else if (e instanceof UserNotFoundException) {
            return Code.USER_NOT_FOUND;
        } 
        // ...
        return null;
    }
}
