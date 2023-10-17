package com.integrated.demousermanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.model.mapper.UserMapper;
import com.integrated.demousermanagementsystem.repository.UserInfoRepository;
import com.integrated.demousermanagementsystem.service.UserInfoService;

@Service
public class UserInfoServiceImp implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        List<UserInfo> users = userInfoRepository.findAll();
        return users.stream()
                .map(userMapper::map) // Use the UserMapper to map UserInfo to UserDTO
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public void signUp(UserSignUpDTO userSignUpDTO) {
        UserInfo newUser = userMapper.map(userSignUpDTO);
        userInfoRepository.save(newUser);
    }

    @Override
    public UserDTO signIn(String usernameOrEmail, String password) {
        // Find user by username or email
        UserInfo user = userInfoRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            return userMapper.map(user);
        }

        // Return null if user not found or password doesn't match
        return null;
    }

    @Override
    public UserDTO resetPassword(String usernameOrEmail, String oldPassword, String newPassword) {
        // First, attempt to sign in to validate the old password
        UserDTO signedInUser = signIn(usernameOrEmail, oldPassword);

        // If signIn succeeds, change the password
        if (signedInUser != null) {
            // Find the user by username or email
            UserInfo user = userInfoRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

            // Update the password
            user.setPassword(newPassword);
            UserInfo updatedUser = userInfoRepository.save(user);

            // Map the updated user to DTO and return
            return userMapper.map(updatedUser);
        }

        // If sign-in fails, throw a RuntimeException or a custom exception
        throw new RuntimeException("Authentication failed. Invalid username/email or old password.");
    }

}
