package com.integrated.demousermanagementsystem.repository;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrated.demousermanagementsystem.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long>{
  
    // Custom query method to find user by username or email
    UserInfo findByUsernameOrEmail(String username, String email);

    Optional<UserInfo> findByUsername (String username);

    Object save(User any);
}
