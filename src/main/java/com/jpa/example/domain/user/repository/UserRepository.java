package com.jpa.example.domain.user.repository;

import com.jpa.example.domain.user.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByAccountId(String AccountId);

  Optional<User> findByAccountIdOrNickname(String accountId, String Nickname);

}
