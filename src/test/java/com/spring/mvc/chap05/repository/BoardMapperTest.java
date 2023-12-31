package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {
    @Autowired
    BoardMapper mapper;

    // 게시물을 300개 저장
    @Test
    @Rollback
    @DisplayName("게시물 300개를 저장해야 한다")
    void bulkInsertTest() {
        //given
        for (int i = 1; i < 300 ; i++) {
            Board  b = Board.builder()
                    .content("테스트용 내용" + i)
                    .title("테스트용 제목" + i)
                    .build();
            mapper.save(b);
        }
        //when

        //then
    }

    @Test
    @DisplayName("게시물을 전체조회하면 300개의 게시물이 조회해야 한다.")
    void findAllTest() {
        //given

        //when
        List<Board> boardList = mapper.findAll(new Search());
        //then
        assertTrue(boardList.size() == 299);
    }
    
    
    @Test
    @DisplayName("20번 게시물을 단일조회하면 제목에 8이 포함되어야 한다.")
    void findOneTest() {
        //given
        int boardNo = 30;
        //when
        Board board = mapper.findOne(boardNo);
        //then
        assertTrue(board.getTitle().contains("8"));
    }

    @Test
    @DisplayName("29번 게시물을 삭제하고 다시 조회하면 조회되지 않아야 한다")
    @Transactional
    @Rollback
    void deleteTest() {
        //given
        int boardNo = 29;
        //when
        boolean b = mapper.deleteByNo(boardNo);
        Board board = mapper.findOne(boardNo);
        //then
        assertTrue(b);
        assertNull(board);
    }


}