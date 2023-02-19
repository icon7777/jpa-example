package com.jpa.example.domain.board.service.impl;

import com.jpa.example.domain.board.Board;
import com.jpa.example.domain.board.dto.request.RequestBoardDTO.CreateBoard;
import com.jpa.example.domain.board.dto.request.RequestBoardDTO.UpdateBoard;
import com.jpa.example.domain.board.dto.response.ResponseBoardDTO;
import com.jpa.example.domain.board.dto.response.ResponseBoardsDTO;
import com.jpa.example.domain.board.repository.BoardRepository;
import com.jpa.example.domain.board.service.BoardService;
import com.jpa.example.domain.user.User;
import com.jpa.example.domain.user.service.UserService;
import com.jpa.example.global.enums.ResponseCode;
import com.jpa.example.global.exception.BadRequestException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepo;

  private final UserService userService;

  @Override
  public Board getBoardById(Long boardId) {
    return boardRepo.findByBoardIdAndEnabledTrue(boardId)
        .orElseThrow(() -> BadRequestException.of(ResponseCode.BY0001,"board.id.not.find"));
  }

  @Override
  public Page<ResponseBoardsDTO> getBoards(final Pageable pageable) {
    User user = userService.getUserBySelf();
    return boardRepo.findResponseBoards(pageable, user);
  }

  @Override
  public ResponseBoardDTO getBoardByBoardId(final Long boardId) {
    User user = userService.getUserBySelf();
    return boardRepo.findResponseBoard(boardId, user)
        .orElseThrow(() -> BadRequestException.of(ResponseCode.BY0001,"board.id.not.find"));
  }

  @Override
  public Long createBoard(final CreateBoard createBoard) {
    User user = userService.getUserBySelfByNotnull();
    Board board = Board.builder()
        .boardTitle(createBoard.getBoardTitle())
        .boardContent(createBoard.getBoardContent())
        .user(user)
        .build();
    return boardRepo.save(board).getBoardId();
  }

  @Override
  public void updateBoard(final Long boardId, final UpdateBoard updateBoard) {
    boardUserCheck(boardId);
    Board board = getBoardById(boardId);
    board.changeTitle(updateBoard.getBoardTitle());
    board.changeContent(updateBoard.getBoardContent());
    boardRepo.save(board);
  }

  @Override
  public void deleteBoard(Long boardId) {
    boardUserCheck(boardId);
    Board board = getBoardById(boardId);
    board.changeEnable(false);
    boardRepo.save(board);
  }

  public void boardUserCheck(Long boardId) {
    User user = userService.getUserBySelfByNotnull();
    Board board = getBoardById(boardId);
    if(!Objects.equals(board.getUser().getUserId(), user.getUserId())){
      throw BadRequestException.of(ResponseCode.BY0002,"board.user.net.match");
    }
  }

}
