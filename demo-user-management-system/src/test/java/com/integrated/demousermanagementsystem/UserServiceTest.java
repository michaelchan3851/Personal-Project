package com.integrated.demousermanagementsystem;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.integrated.demousermanagementsystem.check.ValidationUtils;
import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.repository.UserInfoRepository;
import com.integrated.demousermanagementsystem.service.UserInfoService;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserInfoService userInfoService;

  @MockBean
  private UserInfoRepository userInfoRepository;

  @Test
  void testIsUsernameValid() {
    assertEquals(true, ValidationUtils.isUsernameValid("ABCss123"));
    assertEquals(true, ValidationUtils.isUsernameValid("ABC^&!!#ss123"));
    assertEquals(true, ValidationUtils.isUsernameValid("ssOE23"));
  }

  @Test
  void testSignUp() throws UserException {
        
    UserSignUpDTO dto = UserSignUpDTO.builder()
        .username("michaelchan8")
        .password("Admin1234") //
        .name("michael") //
        .gender("M")
        .DOB(LocalDate.of(2000, 11, 11)) //
        .address("ABC Building") //
        .contact("12345678") //
        .email("hello@gmail.com") //
        .build();
    UserDTO expectedUser = UserDTO.builder() //
        .role("Normal") //
        .name("michael")
        .gender("M")
        .DOB(LocalDate.of(2000, 11, 11))
        .address("ABC Building")
        .contact("12345678")
        .email("hello@gmail.com")
        .lastLogin(LocalDateTime.now())
        .build();
    assertEquals(expectedUser, userInfoService.signUp(dto));

  }

  
}