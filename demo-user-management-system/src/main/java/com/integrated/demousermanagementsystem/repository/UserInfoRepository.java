package com.integrated.demousermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrated.demousermanagementsystem.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long>{
  
    // Custom query method to find user by username or email
    UserInfo findByUsernameOrEmail(String username, String email);
}
