package com.spring.mvc.chap05.dto.request;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyPostRequestDTO {

    // @NotNull // null만 안 됨! 빈 문자열은 됨!
    @NotBlank // null 안돼 빈문자열도 안돼
    @Size(min = 1, max = 300)
    private String text; // 댓글 내용
    @NotBlank
    @Size(min = 2, max = 5)
    private String author;
    @NotNull
    private Long bno;

    public Reply toEntity(){
        return Reply.builder()
                .replyText(this.text)
                .replyWriter(this.author)
                .boardNo(Math.toIntExact(bno))
                .build()
                ;
    }

}
