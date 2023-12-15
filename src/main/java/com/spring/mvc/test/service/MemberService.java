package com.spring.mvc.test.service;

import com.spring.mvc.test.dto.MemberRequestDTO;
import com.spring.mvc.test.entity.Member;
import com.spring.mvc.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    public List<Member> memberAll(){
        return repository.findAll();
    }
    public boolean newMember(Member member){
        return repository.save(member);
    }

    public String login(MemberRequestDTO dto) {
        Member m = repository.findOne(dto.getId());
        if (m != null) {
            if (dto.getId().equals(m.getId())) {
                if (dto.getPassword().equals(m.getPassword())) {
                    return "성공";
                }
                return "아이디만 맞음";
            }
        }
        return "병신";

    }
}
