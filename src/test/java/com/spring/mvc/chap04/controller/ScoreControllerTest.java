package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreMapper;
import com.spring.mvc.chap04.service.ScoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ScoreControllerTest {
    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("score객체 하나를 받아서 수정하면 값이 변경되어야 한다")
    void updateScoreTest() {
        //given
        int kor = 10;
        int eng = 10;
        int math = 100;
        Score score = mapper.findOne(7);
        //when
        mapper.updateScore(kor, eng, math, 7);
        //then
        assertEquals(10, score.getEng());
    }



}