package com.spring.mvc.test.repository;


import com.spring.mvc.test.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//인 메모리 저장
//@Repository
public class MemberRepositoryImpl implements MemberRepository{
    private static final Map<String, Member> map;

    static {
        map = new HashMap<>();
        Member m1 = new Member("jbj3713", "wjdqjawns12", "정범준");
        Member m2 = new Member("kqh123", "rudrbgus12", "경규현");
        Member m3 = new Member("sjs123", "tjwnstjr12", "서준석");

        map.put(m1.getId(), m1);
        map.put(m2.getId(), m2);
        map.put(m3.getId(), m3);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(map.values())
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Member findOne(String id) {
        return map.get(id);
    }

    @Override
    public boolean delete(String id) {
        map.remove(id);
        return true;
    }

    @Override
    public boolean save(Member member) {
        if(map.containsKey(member.getId())) return false;
        map.put(member.getId(), member);
        return true;
    }

    @Override
    public void updateUserName(Member member) {
    }
}
