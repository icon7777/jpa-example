package com.jpa.example.domain.board.repository;

import com.jpa.example.domain.board.dto.response.ResponseBoardDTO;
import com.jpa.example.domain.board.dto.response.ResponseBoardsDTO;
import com.jpa.example.domain.user.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepo {

  public Page<ResponseBoardsDTO> findResponseBoards(Pageable pageable, User user);

  public Optional<ResponseBoardDTO> findResponseBoard(Long boardId, User user);
}
