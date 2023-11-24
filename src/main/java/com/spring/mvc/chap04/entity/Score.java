package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;
import org.springframework.stereotype.Controller;



/*
    엔터티 클래스
    - 데이터베이스에 저장할 데이터 조회할 데이터를 자바 클래스에 매칭
 */
@Setter @Getter
@ToString @EqualsAndHashCode // 해시코드
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 생성자
public class Score {
    private String name; // 학생이름
    private int kor, eng, math; // 국영수 점수
    private int stuNum; // 힉반
    private int total; // 총점
    private double average; // 평균
    private Grade grade; // 학점

    public Score(ScoreRequestDTO score) {
        convertInputData(score);
        calculateTotalAndAverage();
        makeGrade();
    }

    private void makeGrade() {
        if (average >= 90) this.grade = Grade.A;
        else if (average >= 80) this.grade = Grade.B;
        else if (average >= 70) this.grade = Grade.C;
        else if (average >= 60) this.grade = Grade.D;
        else this.grade = Grade.F;
    }

    private void calculateTotalAndAverage() {
        this.total = kor + math + eng;
        this.average = total / 3;
    }

    private void convertInputData(ScoreRequestDTO score) {
        this.name = score.getName();
        this.kor = score.getKor();
        this.math = score.getMath();
        this.eng = score.getEng();
    }
}
