package com.jpa.example.domain.nice.service.impl;

import com.jpa.example.domain.board.Board;
import com.jpa.example.domain.board.service.BoardService;
import com.jpa.example.domain.nice.Nice;
import com.jpa.example.domain.nice.repository.NiceRepository;
import com.jpa.example.domain.nice.service.NiceService;
import com.jpa.example.domain.user.User;
import com.jpa.example.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NiceServiceImpl implements NiceService {

  private final NiceRepository niceRepository;

  private final UserService userService;

  private final BoardService boardService;

  @Override
  public void saveNice(Long boardId, Boolean enabled) {

    User user = userService.getUserBySelfByNotnull();
    Board board = boardService.getBoardById(boardId);
    Nice nice = niceRepository.findByBoardAndUserAndEnabledTrue(board, user)
        .orElse(Nice.builder().board(board).user(user).build());
    nice.changeEnable(enabled);
    niceRepository.save(nice);
  }
}
