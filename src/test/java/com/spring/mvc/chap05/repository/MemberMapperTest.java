package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Auth;
import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {
    @Autowired
    MemberMapper mapper;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("정범준이라는 사람을 저장하면 true가 리턴 되어야 한다.")
    void saveTest() {
        //given
        Member member =
                new Member(
                        "jbj3713", encoder.encode("1234"), "정범준",
                        "ddc@naver.com", Auth.COMMON, LocalDateTime.now()
                );
        //when
        boolean save = mapper.save(member);
        //then
        assertTrue(save);
    }

    @Test
    @DisplayName("jbj3713을 한명을 조회하면 그 사람의 password는 1234이어야 한다")
    void findMemberTest() {
        //given
        String account = "jbj3713@naver.com";
        //when
        Member member = mapper.findMember(account);
        //then
        assertEquals("1234", member.getPassword());
    }

    @Test
    @DisplayName("계정이 newJense일 경우 결과 값은 false이어야 한다")
    void isDuplicateTest() {
        //given
        String acc = "newjense";
        //when
        boolean account = mapper.isDuplicate("account", acc);

        //then
        assertFalse(account);
    }

    @Test
    @DisplayName("이메일 abc@naver.com일 경우 결과 값은 true이어야 한다")
    void isDuplicateTest2() {
        //given
        String email = "abc@naver.com";
        //when
        boolean b = mapper.isDuplicate("email", email);
        //then
        assertTrue(b);
    }

    @Test
    @DisplayName("비밀번호가 암호회 되어야 한다")
    void encodingTest() {
        // 인코딩 전 패스워드
        String rawPassword = "abc1234!@";

        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("rawPassword = " + rawPassword);
        System.out.println("encodedPassword = " + encodedPassword);
    }


}