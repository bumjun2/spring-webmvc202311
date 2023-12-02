package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap05.entity.Board;

import java.util.List;

public interface BoardRepository {
    List<Board> findAll();
    boolean save(Board board);
    boolean delete(int boardNo);
    Board findOne(int bno);
}
