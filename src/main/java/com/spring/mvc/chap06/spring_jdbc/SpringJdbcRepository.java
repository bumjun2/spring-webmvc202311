package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.jdbc.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpringJdbcRepository {

    // 데이터 소스 설정 : 데이터베이스 접속 정복 설정 (url, id, pw....)
    // application.properties 파일에 작성

    // JdbcTemplate 빈 의존성 주입
    private final JdbcTemplate template;

    // INSERT 기능
    public void save(Person p){
        String sql = "INSERT INTO person (id, person_name, person_age) " +
                "VALUES(?,?,?)";
        template.update(sql, p.getId(), p.getPersonName(), p.getPersonAge());
    }

    public void remove(String id){
        String sql = "DELETE FROM person WHERE id = ?";
        template.update(sql, id);
    }

    // update 기능
    public void modify(Person p){
        String sql = "UPDATE person SET person_name = ?" +
                ", person_age = ? WHERE id = ?";
        template.update(sql, p.getPersonName(), p.getPersonAge(), p.getId());
    }

    //select
    public List<Person> findAll(){
        String sql = "SELECT * FROM person";
        // rowMapper : DB에서 조회한 것들을 객체로 어떻게 매칠할지 설정
        return template.query(sql, (rs, rn) -> new Person(rs));
    }

    // 단일 조회
    public Person findOne(String id){
        String sql = "SELECT * FROM person WHERE id = ?";
        return template.queryForObject(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Person(rs);
            }
        }, id);
    }


    public void save2(Person p){
        String sql = "INSERT INTO person " +
                "(id, person_name, person_age) " +
                "VALUES (?, ?, ?)"
                ;
        template.update(sql, p.getId(), p.getPersonName(), p.getPersonAge());
    }

    public void remove2(String id){
        String sql = "DELETE FROM person " +
                "WHERE id = ?"
                ;
        template.update(sql, id);
    }

    public void modify2(Person p){
        String sql = "UPDATE person " +
                "SET person_name = ?, person_age = ? " +
                "WHERE id = ?"
                ;
        template.update(sql, p.getPersonName(), p.getPersonAge(), p.getId());
    }

    public List<Person> findAll2(){
        String sql = "SELECT * FROM person";
        return template.query(sql, (rs, rowNum) -> new Person(rs));
    }

    public Person findOne2(String id){
        String sql = "SELECT * FROM person WHERE id = ?";
        return  template.queryForObject(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Person(rs);
            }
        }, id);
    }
}
