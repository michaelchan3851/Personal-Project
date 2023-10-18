package com.integrated.demousermanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.exception.UserNotFoundException;
import com.integrated.demousermanagementsystem.infra.Code;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.model.mapper.UserMapper;
import com.integrated.demousermanagementsystem.repository.UserInfoRepository;
import com.integrated.demousermanagementsystem.service.UserInfoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserInfoServiceImp implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() throws UserException {
        try {
            List<UserInfo> users = userInfoRepository.findAll();
            return users.stream()
                    .map(e -> userMapper.map(e)) // Use the UserMapper to map UserInfo to UserDTO
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle the exception or log it for debugging purposes
            // You can also throw a more specific UserException with a specific error code
            // if needed
            throw new UserException(Code.VALIDATOR_FAILURE);
        }
    }

    @Override
    public void deleteById(Long id) throws UserException {
        try {
            userInfoRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new UserException(Code.VALIDATOR_FAILURE);
        }
    }

    @Override
    public UserDTO signUp(UserSignUpDTO SignUpUser) {
        UserInfo newUser = userMapper.map(SignUpUser);
        UserInfo savedUser = userInfoRepository.save(newUser);
        return userMapper.map(savedUser);
        // Return true if user is saved successfully, false otherwise
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

    @Override
    public UserDTO getProfile(String username) {
        return userMapper.map(findUserByUsername(username));
    }

    @Override
    public UserInfo findUserByUsername(String username) {
        return userInfoRepository.findAll().stream() //
                .filter(e -> e.getUsername().equals(username)) //
                .findAny() //
                .orElseThrow(() -> new EntityNotFoundException()); //
    }
}
