package com.spring.mvc.test.repository;


import com.spring.mvc.test.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberSpringJdbc implements MemberRepository{

    private final JdbcTemplate template;

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM tbl_people";
        return template.query(sql, (rs, rowNum) -> new Member(rs));
    }

    @Override
    public Member findOne(String id) {
        String sql = "SELECT * FROM tbl_people " +
                "WHERE id = ?";
        return template.queryForObject(sql, (rs, rowNum) -> new Member(rs), id);
    }



    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM tbl_people " +
                "WHERE id = ?";
        return template.update(sql, id) == 1;
    }

    @Override
    public boolean save(Member member) {
        String sql = "INSERT INTO tbl_people " +
                "(id, password, username) " +
                "VALUES (?, ?, ?)"
                ;
        return template.update(sql, member.getId(), member.getPassword(), member.getUserName()) == 1;
    }

    @Override
    public void updateUserName(Member member) {
        String sql = "UPDATE tbl_people " +
                "SET id = ?, password = ?, username = ? " +
                "WHERE id = ?"
                ;
        template.update(sql, member.getId(), member.getPassword(), member.getUserName(), member.getId());
    }
}
