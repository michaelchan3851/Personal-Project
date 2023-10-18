package com.integrated.demousermanagementsystem.check;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.exception.UserExistedException;
import com.integrated.demousermanagementsystem.infra.Code;
import com.integrated.demousermanagementsystem.repository.UserInfoRepository;

public class ValidationUtils {

  private static final String[] EMAIL_FORMATS = {
      "@gmail.com", "@icloud.com", "@hotmail.com", "@hotmail.com.hk"
  };

  public static boolean isEmailValid(String email) {
    String emailRegex = String.join("|", EMAIL_FORMATS);
    if (Pattern.matches(".*" + emailRegex + ".*", email)) {
      return true;
    } else {
      throw new IllegalArgumentException("Invalid email");
    }
  }

  public static boolean isUsernameValid(String username) {
    if (username.length() >= 6) {
      return true;
    } else {
      throw new IllegalArgumentException("Invalid Username");
    }
  }

  public static boolean isPasswordValid(String password) {
    // Password must be 6-15 characters long and contain at least one uppercase, one
    // lowercase, and one digit
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,15}$";
    if (Pattern.matches(passwordRegex, password)) {
      return true;
    } else {
      throw new IllegalArgumentException("Invalid Password");
    }
  }

}
