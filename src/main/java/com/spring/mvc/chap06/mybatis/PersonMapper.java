package com.spring.mvc.chap06.mybatis;

import com.spring.mvc.chap06.jdbc.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 마이바티스의 sql실행을 위한 인터페이스
@Mapper
public interface PersonMapper {
    //CRUD를 명세
    boolean save(Person p);
    boolean update(Person p);
    boolean delete(String id);
    List<Person> findAll();
    Person findOne(String id);
}
