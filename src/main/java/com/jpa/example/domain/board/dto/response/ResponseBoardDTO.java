package com.jpa.example.domain.board.dto.response;

import com.jpa.example.global.enums.accountType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResponseBoardDTO {

  private Long boardId;
  private String boardTitle;
  private String boardContent;
  private LocalDateTime createdDt;
  private String userNickname;
  private String accountTypeName;
  private Long niceCount;
  private Boolean niceByMe;

  public ResponseBoardDTO(Long boardId,
      String boardTitle,
      String boardContent,
      LocalDateTime createdDt,
      String userNickname,
      accountType accountType,
      Long niceCount,
      Boolean niceByMe) {

    this.boardId = boardId;
    this.boardTitle = boardTitle;
    this.boardContent = boardContent;
    this.createdDt = createdDt;
    this.userNickname = userNickname;
    this.accountTypeName = accountType.getValue();
    this.niceCount = niceCount;
    this.niceByMe = niceByMe;
  }

}
