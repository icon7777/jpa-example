package com.jpa.example.domain.board;

import com.jpa.example.domain.user.User;
import com.jpa.example.global.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  private String boardTitle;

  private String boardContent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  private User user;

  private Boolean enabled;

  @Builder
  private Board(
      final String boardTitle,
      final String boardContent,
      final User user) {
    this.boardTitle = boardTitle;
    this.boardContent = boardContent;
    this.user = user;
    this.enabled = true;
  }

  public void changeEnable(final Boolean enabled) {this.enabled = enabled;}
  public void changeTitle(final String boardTitle) {this.boardTitle = boardTitle;}
  public void changeContent(final String boardContent) {
    this.boardContent = boardContent;
  }
}
