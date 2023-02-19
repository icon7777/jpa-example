package com.jpa.example.global.loadListener;

import com.jpa.example.domain.board.Board;
import com.jpa.example.domain.board.service.BoardService;
import com.jpa.example.domain.nice.Nice;
import com.jpa.example.domain.nice.repository.NiceRepository;
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
public class NiceLoaderListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {

  private final BoardService boardService;

  private final UserService userService;

  private final NiceRepository niceRepository;

  @Override
  public int getOrder() {
    return 2;
  }

  @Override
  public void onApplicationEvent(final ApplicationStartedEvent event) {

    Board board = boardService.getBoardById(1L);
    Board board2 = boardService.getBoardById(2L);

    User user = userService.getUserById(1L);

    Nice nice1 = Nice.builder()
        .user(user)
        .board(board)
        .build();

    Nice nice2 = Nice.builder()
        .user(user)
        .board(board2)
        .build();



    createNiceAssertNull(nice1);
    createNiceAssertNull(nice2);
  }

  private void createNiceAssertNull(final Nice nice) {
    if (ObjectUtil.isEmpty(niceRepository.findByBoardAndUserAndEnabledTrue(nice.getBoard(), nice.getUser()))) {
      niceRepository.save(nice);
    }
  }

}
