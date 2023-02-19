package com.jpa.example.domain.board.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.jpa.example.domain.board.dto.request.RequestBoardDTO;
import com.jpa.example.domain.board.dto.response.ResponseBoardDTO;
import com.jpa.example.domain.board.service.BoardService;
import com.jpa.example.global.core.CommonResponse;
import com.jpa.example.global.core.PageWrapper;
import com.jpa.example.global.core.SuccessData;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping
  @ResponseStatus(OK)
  public CommonResponse<PageWrapper> getBoards(@RequestParam(value = "page", defaultValue = "0", required = false) @Min(0) int page,
                                               @RequestParam(value = "size", defaultValue = "10", required = false) @Min(1) @Max(100) final int size
  ) {
    return CommonResponse.from(PageWrapper.of(boardService.getBoards(PageRequest.of(page, size))));
  }

  @GetMapping("/{boardId}")
  @ResponseStatus(OK)
  public CommonResponse<ResponseBoardDTO> getBoardByBoardId(@PathVariable final Long boardId) {
    return CommonResponse.from(boardService.getBoardByBoardId(boardId));
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public CommonResponse<Long> createBoard(@RequestBody @Valid final RequestBoardDTO.CreateBoard createBoard) {
    return CommonResponse.from(boardService.createBoard(createBoard));
  }

  @PutMapping("/{boardId}")
  @ResponseStatus(CREATED)
  public CommonResponse<SuccessData> updateBoard(@PathVariable final Long boardId,
                                                 @RequestBody @Valid final RequestBoardDTO.UpdateBoard updateBoard
  ) {
    boardService.updateBoard(boardId,updateBoard);
    return CommonResponse.from(SuccessData.of());
  }

  @DeleteMapping("/{boardId}")
  @ResponseStatus(CREATED)
  public CommonResponse<SuccessData> deleteBoard(@PathVariable final Long boardId) {
    boardService.deleteBoard(boardId);
    return CommonResponse.from(SuccessData.of());
  }

}
