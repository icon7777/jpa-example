package com.jpa.example.domain.user.service.impl;

import com.jpa.example.domain.user.User;
import com.jpa.example.domain.user.repository.UserRepository;
import com.jpa.example.domain.user.service.UserService;
import com.jpa.example.global.enums.ResponseCode;
import com.jpa.example.global.exception.BadRequestException;
import com.jpa.example.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepo;

  @Override
  public User getUserById(Long userId) {
    return userRepo.findById(userId)
        .orElseThrow(() -> BadRequestException.of(ResponseCode.UY0001, "user.id.not.find"));
  }

  @Override
  public User getUserBySelf() {
    return userRepo.findByAccountId(SecurityUtil.getAccountId())
        .orElse(null);
  }

  @Override
  public User getUserBySelfByNotnull() {
    return userRepo.findByAccountId(SecurityUtil.getAccountId())
        .orElseThrow(() -> BadRequestException.of(ResponseCode.UY0002, "user.accountId.not.find"));
  }

}
