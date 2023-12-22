//package com.spring.mvc.test.repository;
//
//import com.spring.mvc.test.entity.Member;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class MemberSpringJdbcTest {
//
////    private final MemberRepository repository;
//
//    public MemberSpringJdbcTest(@Qualifier("dd") MemberRepository repository){
//        this.repository = repository;
//    }
////
//    @Test
//    @DisplayName("추가")
//    void saveTest() {
//        //given
////        for (int i = 0; i < 30; i++) {
////            Member member = new Member("" + Math.random(), "" + i, "말포이" + i);
////            repository.save(member);
////        }
////        //when
////        all.forEach(System.out::println);
////        List<Member> all = repository.findAll();
//        //then
//    }
//
//
//
//    @Test
//    @DisplayName("업그레이드")
//    void updateTest() {
//        //given
//        Member member = new Member("0.9335379348089432", "3333", "정범준");
//        //when
//        repository.updateUserName(member);
//        //then
//    }
//
//    @Test
//    @DisplayName("삭제")
//    void deleteTest() {
//        //given
//        String id = "0.6306033515897433";
//        //when
//        repository.delete(id);
//        //then
//    }
//
//    @Test
//    @DisplayName("한명 찾기")
//    void findOneTest() {
//        //given
//        String id = "0.33924027819951474";
//        //when
//        Member one = repository.findOne(id);
//        //then
//        assertEquals("2", one.getPassword());
//    }
//
//
//
//
//}