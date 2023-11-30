package com.spring.mvc.chap05.dto;


import com.spring.mvc.chap05.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BoardRequestDTO {
    int boardNo;
    String title;
    String content;
    LocalDateTime date;

    public BoardRequestDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getShortContent();
        this.date = board.getDate();
    }
}
