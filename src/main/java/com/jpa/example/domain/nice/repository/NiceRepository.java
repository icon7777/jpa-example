package com.jpa.example.domain.nice.repository;

import com.jpa.example.domain.board.Board;
import com.jpa.example.domain.nice.Nice;
import com.jpa.example.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NiceRepository extends JpaRepository<Nice,Long> {

  Optional<Nice> findByBoardAndUserAndEnabledTrue(Board board, User user);

}
