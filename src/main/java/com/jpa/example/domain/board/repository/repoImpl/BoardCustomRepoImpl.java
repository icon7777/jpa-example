package com.jpa.example.domain.board.repository.repoImpl;

import com.jpa.example.domain.board.dto.response.ResponseBoardDTO;
import com.jpa.example.domain.board.dto.response.ResponseBoardsDTO;
import com.jpa.example.domain.board.repository.BoardCustomRepo;
import com.jpa.example.domain.user.User;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepoImpl implements BoardCustomRepo {

  private final EntityManager em;

  @Override
  public Page<ResponseBoardsDTO> findResponseBoards(Pageable pageable, User user) {
    List<ResponseBoardsDTO> resultList = em.createQuery(
        "select new com.jpa.example.domain.board.dto.response.ResponseBoardsDTO("
            + "bo.boardId, bo.boardTitle, bo.createdDt, us.nickname, us.accountType, "
            + "(select count(*) from Nice ni where ni.board = bo and ni.enabled = true),"
            + "(select count(*) > 0 from Nice ni where ni.board = bo and ni.user = :user and ni.enabled = true)"
            + ")"
            + "from Board bo "
            + "left outer join bo.user us "
            + "where bo.enabled = true "
            + "and us.quit = true", ResponseBoardsDTO.class)
        .setParameter("user", user)
        .setFirstResult( pageable.getPageNumber() * pageable.getPageSize() )
        .setMaxResults(pageable.getPageSize())
        .getResultList();

    Long totalElement = em.createQuery("Select count(*) from Board where enabled = true", Long.class).getSingleResult();


    return new PageImpl<>(resultList,pageable,totalElement);
  }

  @Override
  public Optional<ResponseBoardDTO> findResponseBoard(Long boardId, User user) {
    return em.createQuery(
        "select new com.jpa.example.domain.board.dto.response.ResponseBoardDTO("
            + "bo.boardId, bo.boardTitle, bo.boardContent, bo.createdDt, us.nickname, us.accountType, "
            + "(select count(*) from Nice ni where ni.board = bo and ni.enabled = true),"
            + "(select count(*) > 0 from Nice ni where ni.board = bo and ni.user = :user and ni.enabled = true)"
            + ")"
            + "from Board bo "
            + "left outer join bo.user us "
            + "where bo.boardId = :boardId "
            + "and bo.enabled = true "
            + "and us.quit = true", ResponseBoardDTO.class)
        .setParameter("user", user)
        .setParameter("boardId", boardId)
        .getResultList().stream().findAny();
  }
}
