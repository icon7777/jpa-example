package com.jpa.example.domain.nice;

import com.jpa.example.domain.board.Board;
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
public class Nice extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long niceId;

  private Boolean enabled;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  private Board board;

  @Builder
  private Nice(
      final User user,
      final Board board) {
    this.user = user;
    this.board = board;
    this.enabled = true;
  }

  public void changeEnable(final Boolean enabled) {this.enabled = enabled;}

}
