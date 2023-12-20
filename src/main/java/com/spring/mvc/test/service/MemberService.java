package com.spring.mvc.test.service;

import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.test.dto.MemberRequestDTO;
import com.spring.mvc.test.dto.MemberResponseDto;
import com.spring.mvc.test.entity.Member;
import com.spring.mvc.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;


    public List<MemberResponseDto> memberAll(){
        return repository.findAll()
                .stream()
                .map(member -> new MemberResponseDto(member))
                .collect(Collectors.toList());
    }

    public Member memberOne(String id){
        Member member = repository.findOne(id);
        if (member != null) return member;
        else return null;
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

    public boolean remove(String id){
        return repository.delete(id);
    }
}
