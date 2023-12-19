package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback
class ReplyMapperTest {
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    BoardMapper boardMapper;

    @Test
    @DisplayName("게시물 100개를 등록하고 랜덤으로 1000개의 댓글을 게시물에 등록한다")
    void bulkInsertTest() {
        //given
//        for (int i = 1; i <= 100; i++) {
//            Board b = Board.builder()
//                    .title("재밌는 글" + i)
//                    .content("응 개노잼 !" + i)
//                    .build();
//            boardMapper.save(b);
//        }
//
//        for (int i = 1; i <= 1000; i++) {
//            Reply r = Reply.builder()
//                    .replyText("하하하하" + i)
//                    .replyWriter("잼민이" + i)
//                    .boardNo((int) (Math.random() * 100 + 1))
//                    .build();
//            replyMapper.save(r);
//        }
        //when

        //then
    }
    

//    void findAllTest() {
//        //given
//        int boardNo = 77;
//        //when
//        List<Reply> replyList = replyMapper.findAll(boardNo);
//        //then
//        assertEquals(16, replyList.size());
//        assertEquals("잼민이69", replyList.get(0).getReplyWriter());
//    }


    @Test
    @DisplayName("77게시물에 708번 댓글을 삭제하면 708번 댓글은 조회되지 않을 것이고" +
            "77번을 전체 조회하면 리스트의 사이즈는 15이어야 한다")
    void deleteTest() {
        //given
        int boardNo = 77;
        long replyNo = 708;
        //when
        replyMapper.delete(replyNo);
        Reply reply = replyMapper.findOne(replyNo);
        //then
        assertNull(reply);
//        assertEquals(15, replyMapper.findAll(boardNo).size());
    }

    @Test
    @DisplayName("103번 갯글의 댓글 내용을 수정하면, 다시 조회앴을 때 수정된 내용이 조회되어야한다.")
    void modifyTest() {
        //given
        long replyNo = 708;
        String changText = "호호호호호";
        //when
        Reply re = replyMapper.findOne(replyNo);
        re.setReplyText(changText);
        replyMapper.modify(re);
        //then
        assertEquals("호호호호호", re.getReplyText());
    }

    
}