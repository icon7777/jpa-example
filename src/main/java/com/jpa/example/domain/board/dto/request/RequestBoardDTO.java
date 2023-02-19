package com.jpa.example.domain.board.dto.request;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RequestBoardDTO {

  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor
  @Getter
  public static class CreateBoard {

    @NotBlank
    private String boardTitle;
    @NotBlank
    @Lob
    private String boardContent;

  }

  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor
  @Getter
  public static class UpdateBoard {

    @NotBlank
    private String boardTitle;
    @NotBlank
    @Lob
    private String boardContent;

  }

}
