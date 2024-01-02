package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SinUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService service;


    @Test
    @DisplayName("회원 정보를 전달하면 바밀번호가 암호화되어 디비에 저장되어야 한다.")
    void joinTest() {
        //given
        SinUpRequestDTO dto = SinUpRequestDTO.builder()
                .account("kityy")
                .password("kkk1234!")
                .name("정범준")
                .email("adfasd")
                .build();
        //when
        boolean join = service.join(dto, null);
        //then
        assertTrue(join);
    }
    @Test
    @DisplayName("계정명이 kityy인 회원의 로그인 시도 결과를 상황별로 검증한다")
    void loginTest() {
        //given
        LoginRequestDTO dto = LoginRequestDTO.builder()
                .account("22")
                .password("12!")
                .build();
        //when
        LoginResults result = service.authenticate(dto, null, null);
        //then
        assertEquals(LoginResults.NO_ACC, result);
    }


}