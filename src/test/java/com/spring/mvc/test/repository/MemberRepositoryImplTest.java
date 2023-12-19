package com.spring.mvc.test.repository;

import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.test.dto.MemberRequestDTO;
import com.spring.mvc.test.entity.Member;
import com.spring.mvc.test.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryImplTest {
    @Autowired
    MemberRepository repository;
    @Autowired
    MemberService service;

    @Test
    @DisplayName("멤버에 사이즈는 3이다.")
    void findAllTest() {
        //given

        //when
        //then
    }

    @Test
    @DisplayName("아이디 jbj3713을 조회하면 해당 멤버가 조회 된다")
    void findOneTest() {
        //given
        String id = "jbj3713";
        //when
        Member member = repository.findOne(id);
        //then
        assertEquals("정범준", member.getUserName());
    }
    @Test
    @DisplayName("아이디 jbj3713을 입력하면 삭제되어야 한다.")
    void deleteTest() {
        //given
        String id = "jbj3713";
        //when
        boolean delete = repository.delete(id);
        //then
        assertTrue(delete);
    }
    
    @Test
    @DisplayName("save를 하면 값이 넣어져야 한다")
    void saveTest() {
        //given
        Member member = new Member("ss", "12", "한기한");
        //when
        boolean save = repository.save(member);
        //then
        assertTrue(save);
    }
}