package com.integrated.demousermanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.integrated.demousermanagementsystem.controller.impl.UserInfoController;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.service.UserInfoService;
import com.integrated.demousermanagementsystem.service.impl.UserInfoServiceImpl;

@WebMvcTest(UserInfoController.class)
  public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
  
    @MockBean
    private UserInfoService userInfoService;
  
    //private UserSignUpDTO signUpDTO = new UserSignUpDTO();
  
    @Test
    void testSignUp() throws Exception{
      UserSignUpDTO signUpDTO = UserSignUpDTO.builder() //
      .name("Michael") //
      .address("ABC Building")
      .contact("987654")
      .email("hello@gmail.com")
      .build();
      UserDTO expectedUser = 
      UserDTO.builder()
      .name("Michael") //
      .email("hello@gmail.com")
      .build();
      when(userInfoService.signUp(signUpDTO)).thenReturn(expectedUser);
      
      mockMvc.perform(post("/api/v1/signup", signUpDTO))
      .andExpect(status().isOk())
      .andDo(print());
    }
  
  }