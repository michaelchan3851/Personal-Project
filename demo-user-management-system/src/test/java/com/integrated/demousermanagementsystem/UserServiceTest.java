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
import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.model.mapper.UserMapper;
import com.integrated.demousermanagementsystem.repository.UserInfoRepository;
import com.integrated.demousermanagementsystem.service.UserInfoService;
// ... (imports and annotations remain unchanged)

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserInfoService userInfoService;

  @MockBean
  private UserInfoRepository userInfoRepository;

  @Test
  void testSignUp() throws UserException {
    UserSignUpDTO dto = UserSignUpDTO.builder()
        .username("michaelchan8")
        .password("Admin1234") //
        .name("michael") //
        .gender("M")
        .dob(LocalDate.of(2000, 11, 11)) //
        .address("ABC Building") //
        .contact("12345678") //
        .email("hello@gmail.com") //
        .build();

    // Mocking the save method of userInfoRepository to return the expected user
    UserDTO expectedUser = UserDTO.builder()
        .id(1L)
        .role("Normal")
        .name("michael")
        .gender("M")
        .dob(LocalDate.of(2000, 11, 11))
        .address("ABC Building")
        .contact("12345678")
        .email("hello@gmail.com")
        // .lastLogin(LocalDateTime.of(2023, 10, 18, 12, 0)) // Use a fixed
        // LocalDateTime for testing
        .build();

    UserMapper userMapper = new UserMapper();
    // Mocking the behavior of userInfoRepository.save(dto)
    UserInfo u = userMapper.map(dto);
    u.setId(1L);
    Mockito.when(userInfoRepository.save(Mockito.any(UserInfo.class))).thenReturn(u);

    // // Perform the signUp operation
    UserDTO actualUser = userInfoService.signUp(dto);

    // // Assert that the returned user matches the expected user
    assertEquals(expectedUser.getDob(), actualUser.getDob());
    assertEquals(expectedUser.getAddress(), actualUser.getAddress());
    assertEquals(expectedUser.getContact(), actualUser.getContact());
    assertEquals(expectedUser.getEmail(), actualUser.getEmail());
  }
}
