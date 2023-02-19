package com.jpa.example.domain.user.service;

import com.jpa.example.domain.user.User;

public interface UserService {

  User getUserById(Long userId);
  User getUserBySelf();
  User getUserBySelfByNotnull();
}
