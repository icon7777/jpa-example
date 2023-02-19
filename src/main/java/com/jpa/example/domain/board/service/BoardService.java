package com.jpa.example.domain.board.service;


import com.jpa.example.domain.board.Board;
import com.jpa.example.domain.board.dto.request.RequestBoardDTO.CreateBoard;
import com.jpa.example.domain.board.dto.request.RequestBoardDTO.UpdateBoard;
import com.jpa.example.domain.board.dto.response.ResponseBoardDTO;
import com.jpa.example.domain.board.dto.response.ResponseBoardsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

  Board getBoardById(Long boardId);

  Page<ResponseBoardsDTO> getBoards(Pageable pageable);

  ResponseBoardDTO getBoardByBoardId(Long boardId);

  Long createBoard(CreateBoard createBoard);

  void updateBoard(Long boardId, UpdateBoard updateBoard);

  void deleteBoard(Long boardId);
}
