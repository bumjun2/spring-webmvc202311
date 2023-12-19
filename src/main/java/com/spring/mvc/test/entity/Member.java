package com.spring.mvc.test.entity;


import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

// 회원 정보
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String id;
    private String password;
    private String userName;

    public Member(ResultSet rs) throws SQLException {
        this.id = rs.getString("id");
        this.password = rs.getString("password");
        this.userName = rs.getString("username");
    }
}
