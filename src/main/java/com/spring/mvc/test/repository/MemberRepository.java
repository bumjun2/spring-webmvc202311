package com.spring.mvc.test.repository;

import com.spring.mvc.test.entity.Member;

import java.util.List;

public interface MemberRepository {
    // 회원 전체 조회
    List<Member> findAll();
    // 회원 한명 조회
    Member findOne(String id);
    // 회원 탈퇴
    boolean delete(String id);
    // 회원 등록
    boolean save(Member member);
    // 이름 수정
    void updateUserName(Member member);
}
