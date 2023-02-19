package com.jpa.example.global.loadListener;

import com.jpa.example.domain.board.Board;
import com.jpa.example.domain.board.repository.BoardRepository;
import com.jpa.example.domain.user.User;
import com.jpa.example.domain.user.service.UserService;
import com.jpa.example.global.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardLoaderListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {

  private final UserService userService;

  private final BoardRepository boardRepository;

  @Override
  public int getOrder() {
    return 1;
  }

  @Override
  public void onApplicationEvent(final ApplicationStartedEvent event) {

    User user = userService.getUserById(1L);

    Board board = Board.builder()
      .boardTitle("게시판테스트1")
      .boardContent("게시판내용1")
      .user(user)
      .build();

    Board board2 = Board.builder()
        .boardTitle("게시판테스트2")
        .boardContent("게시판내용2")
        .user(user)
        .build();

    createBoardAssertNull(board);
    createBoardAssertNull(board2);
  }

  private void createBoardAssertNull(final Board board) {
    if (ObjectUtil.isEmpty(boardRepository.findByBoardTitle(board.getBoardTitle()))) {
      boardRepository.save(board);
    }
  }

}
