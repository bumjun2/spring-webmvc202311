package com.spring.mvc.test.entity;


import lombok.*;

// 회원 정보
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String id;
    private String password;
    private String userName;
}
