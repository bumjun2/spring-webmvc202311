package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> findAll(Page page);
    boolean save(Board board);
    boolean delete(int boardNo);
    Board findOne(int bno);

//    한 클래스에서 사용을 원할때 default 접근 제한자 사용
     void upViewCount(int bno);

     // 총 게시물 수 구하기
    int count();
}
