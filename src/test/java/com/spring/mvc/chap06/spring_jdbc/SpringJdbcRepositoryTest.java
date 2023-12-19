package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.jdbc.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링이 관리하는 빈을 주입받기 위한 아노테이션
class SpringJdbcRepositoryTest {
    @Autowired
    SpringJdbcRepository repository;

    @Test
    @DisplayName("사람 정보를 데이터베이스에 저장한다")
    void saveTest() {
        //given
        Person p = new Person("99", "말똥이", 30);
        //when
        repository.save(p);
        //then
    }

    @Test
    @DisplayName("99번 회원의 이름과 나이를 수정한다.")
    void modifyTest() {
        //given
        String id = "99";
        String newName = "수정수정이";
        int newAge = 50;
        Person p = new Person(id, newName, newAge);
        //when
        repository.modify(p);
        //then
    }
    @Test
    @DisplayName("99번 회원을 삭젝한다")
    void removeTest() {
        //given
        String id = "99";
        //when
        repository.remove(id);
        //then
    }
    @Test
    @DisplayName("전체 조회를 해야한다.")
    void findAllTest() {
        //given

        //when
        List<Person> people = repository.findAll();
        //then
        people.forEach(System.out::println);
    }

    @Test
    @DisplayName("사람 한명을 저장하면 데이터베이스에 추가 되어야 한다")
    void save2Test() {
        //given
        Person p = new Person("123", "정범준", 123);
        //when
        repository.save2(p);
        //then
    }

    @Test
    @DisplayName("수정")
    void modify2Test() {
        //given
        Person p = new Person("123", "박혁거세", 12);
        //when
        repository.modify2(p);
        //then
    }

    @Test
    @DisplayName("삭제")
    void remove2Test() {
        //given
        String id = "333";
        //when
        repository.remove2(id);
        //then
    }

    @Test
    @DisplayName("전체 조회")
    void findAllTest2() {
        //given

        //when
        List<Person> all = repository.findAll2();
        all.forEach(System.out::println);

        //then
    }

    @Test
    @DisplayName("한명 조회")
    void findOneTest2() {
        //given
        String id = "123";
        //when
        Person one2 = repository.findOne2(id);
        System.out.println("one2 = " + one2);
        //then
    }




}