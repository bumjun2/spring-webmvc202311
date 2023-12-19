package com.spring.mvc.test.dto;


import com.spring.mvc.test.entity.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter @Getter @ToString
@EqualsAndHashCode
public class MemberResponseDto {
    private final String id;
    private final String shortUserName;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.shortUserName = makingUserName(member.getUserName());
    }

    private String makingUserName(String userName) {
        String makingName = String.valueOf(userName.charAt(0));
        for (int i = 0; i < userName.length() - 1; i++) {
            makingName += "*";
        }
        return makingName;
    }
}
