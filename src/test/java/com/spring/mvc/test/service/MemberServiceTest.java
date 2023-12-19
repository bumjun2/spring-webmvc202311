package com.spring.mvc.test.service;

import com.spring.mvc.test.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MemberServiceTest {
    private final MemberService service;

    @Autowired
    MemberServiceTest(MemberService service) {
        this.service = service;
    }
    
    @Test
    @DisplayName("추가")
    void memberOneTest() {
        //given
        String id = "0.639424451286511";
        //when
//        Member member = service.addMember(id);
//        System.out.println("member = " + member);
        //then
    }
    
}