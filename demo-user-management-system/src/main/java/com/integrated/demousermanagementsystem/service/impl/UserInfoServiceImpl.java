package com.integrated.demousermanagementsystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.integrated.demousermanagementsystem.check.ValidationUtils;
import com.integrated.demousermanagementsystem.entity.UserInfo;
import com.integrated.demousermanagementsystem.exception.UserException;
import com.integrated.demousermanagementsystem.exception.UserExistedException;
import com.integrated.demousermanagementsystem.infra.Code;
import com.integrated.demousermanagementsystem.model.dto.UserDTO;
import com.integrated.demousermanagementsystem.model.dto.UserSignUpDTO;
import com.integrated.demousermanagementsystem.model.mapper.UserMapper;
import com.integrated.demousermanagementsystem.repository.UserInfoRepository;
import com.integrated.demousermanagementsystem.service.UserInfoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserInfoServiceImpl implements UserInfoService {

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
    public UserDTO signUp(UserSignUpDTO SignUpUser) throws UserException {
        ValidationUtils.isEmailValid(SignUpUser.getEmail());
        ValidationUtils.isPasswordValid(SignUpUser.getPassword());
        ValidationUtils.isUsernameValid(SignUpUser.getUsername());
        try {
            isExitedUser(SignUpUser.getUsername());
            UserInfo newUser = userMapper.map(SignUpUser);
            newUser.setLastLogin(LocalDateTime.now());
            UserInfo savedUser = userInfoRepository.save(newUser);
            return userMapper.map(savedUser);
            // Return true if user is saved successfully, false otherwise
        } catch (UserExistedException e) {
            throw new UserExistedException(Code.ALREADY_EXIST);
        }
    }

    @Override
    public UserDTO signIn(String usernameOrEmail, String password) {
        // Find user by username or email
        UserInfo user = userInfoRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            // Update lastLogin field
            user.setLastLogin(LocalDateTime.now());
            userInfoRepository.save(user); // Save the updated user to the database

            // Return the mapped user
            UserDTO userDTO = userMapper.map(user);
            return userDTO;
        }

        // Return null if user not found or password doesn't match
        return null;
    }

    @Override
    public UserDTO resetPassword(String usernameOrEmail, String oldPassword, String newPassword) throws UserException {
        // First, attempt to sign in to validate the old password
        UserDTO signedInUser = signIn(usernameOrEmail, oldPassword);

        try {
            // If signIn succeeds, change the password
            if (signedInUser != null) {
                // Find the user by username or email
                UserInfo user = userInfoRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

                ValidationUtils.isPasswordValid(newPassword);
                // Update the password
                user.setPassword(newPassword);
                UserInfo updatedUser = userInfoRepository.save(user);

                // Map the updated user to DTO and return
                return userMapper.map(updatedUser);
            }
        } catch (IllegalArgumentException e) {
            throw new UserException(Code.IAE_EXCEPTION);
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

    public boolean isExitedUser(String username) throws UserException {
        if (userInfoRepository.findAll().stream() //
                .filter(e -> e.getUsername().equals(username)) //
                .findAny() //
                .isPresent()) //
            throw new UserExistedException(Code.ALREADY_EXIST);
        return true;
    }
}
