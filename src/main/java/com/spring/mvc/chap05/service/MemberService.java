package com.spring.mvc.chap05.service;


import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SinUpRequestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper mapper;
    private final PasswordEncoder encoder;

    // 회원가입 처리 서비스
    public boolean join(SinUpRequestDTO dto) {
        // 클라이언트가 보낸 회원가입 데이터를
        // 패스워드 인코딩하여 엔터티로 변환해서 전달
        return mapper.save(dto.toEntity(encoder));
    }
    // 로그인 검증 처리
    public LoginResults authenticate(LoginRequestDTO dto) {

        Member foundMember = mapper.findMember(dto.getAccount());

        if (foundMember == null){ // 회원가입 안한 상태
            log.info("{} - 회원가입이 필요합니다.", dto.getAccount());
            return LoginResults.NO_ACC;
        }

        String inputPassword = dto.getPassword();
        String realPassword = foundMember.getPassword();

        if (!encoder.matches(inputPassword, realPassword)){
            log.info("비밀번호가 일치하지 않습니다");
            return LoginResults.NO_PASSWORD;
        }

        log.info("{}님 로그인 성공!", foundMember.getName());
        return LoginResults.SUCCESS;
    }

}
