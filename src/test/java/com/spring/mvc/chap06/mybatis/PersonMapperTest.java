package com.spring.mvc.chap06.mybatis;

import com.spring.mvc.chap06.jdbc.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PersonMapperTest {
    @Autowired
    PersonMapper mapper;
    @Test
    @DisplayName("마이바티스로 데이터베이스에 사람데이터 생성")
    void saveTest() {
        //given
        Person p = new Person("333", "김마바", 50);
        //when
        mapper.save(p);
        //then
    }

    @Test
    @DisplayName("333번 회원을 수정한다")
    void updateTest() {
        //given
        Person p = new Person("333", "마바수정", 99);
        //when
        mapper.update(p);
        //then
    }

    @Test
    @DisplayName("333번을 삭제한다")
    void deleteTest() {
        //given
        String id = "33";
        //when
        mapper.delete(id);
        //then
    }

    @Test
    @DisplayName("person테이블 조회한다")
    void findAllTest() {
        //given

        //when
        List<Person> people = mapper.findAll();
        //then
        people.forEach(System.out::println);
    }



}