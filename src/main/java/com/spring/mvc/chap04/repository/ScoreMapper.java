package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {
//    crud
    List<Score> findAll(String sort);
    //성적 정보 등록
    boolean save(Score score);
    //성적 정보 삭제
    boolean delete(int stuNum);
    //성적 정보 개별 조회
    Score findOne(int stuNum);
    // 성적 정보 업데이트
    void updateScore(int kor, int eng, int math, Grade grade, int stuNum);
}
