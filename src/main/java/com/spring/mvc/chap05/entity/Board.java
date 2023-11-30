package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String shortContent; // 내용
    private int viewCount; // 조회수
    private LocalDateTime date; // 작성일자시간

    public Board(int boardNo, String title, String content) {
        this.boardNo = boardNo;
        this.title = title;
        this.shortContent = content;
        this.date = LocalDateTime.now();
    }


}
