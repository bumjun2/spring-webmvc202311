package com.spring.mvc.test.dto;


import com.spring.mvc.test.entity.Member;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MemberRequestDTO {
    private String id;
    private String password;
}
