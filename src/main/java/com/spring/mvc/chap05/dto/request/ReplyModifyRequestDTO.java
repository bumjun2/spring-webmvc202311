package com.spring.mvc.chap05.dto.request;


import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyModifyRequestDTO {
    @NotNull
    private Long rno;// 수정할 댓글 번호
    @NotBlank
    @Size(min = 2, max = 200)
    private String text; //수정할 댓글 내용
    @NotNull
    private Long bno; // 수정후에 수정될 목록을 조회하기 위해 받은

    public Reply toEntity() {
        return Reply.builder()
                .replyNo(this.rno)
                .replyText(this.text)
                .boardNo(Math.toIntExact(bno))
                .build();
    }

}
