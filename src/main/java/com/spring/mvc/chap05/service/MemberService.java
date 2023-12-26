package com.spring.mvc.chap05.service;


import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SinUpRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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

        Member foundMember = getMember(dto.getAccount());

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

    private Member getMember(String account) {
        return mapper.findMember(account);
    }

    // 아이디 중복 검사 체크
    public boolean checkDuplicateValue(String type, String keyword){
        return mapper.isDuplicate(type, keyword);
    }

    // 세션을 사용해서 일반 로그인 유지
    public void maintainLoginState(HttpSession session, String account){
        // 세션은 서버에서만 유일하게 보관되는 데이터로서
        // 로그인 유지등 상태유지가 필요할 때 사용되는 개념입니다.
        // 세션은 쿠키와 달리 모든 데이터를 저장할 수 있습니다.
        // 세션의 수명은 설정한 수명시간에 영향을 받고 브라우저의 수명과 함께한다.

        // 현재 로그인한 사람의 모든 정보 조회
        Member member = getMember(account);
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .account(member.getAccount())
                .email(member.getEmail())
                .nickName(member.getName())
                .build();

        // 세션에 로그인한 회원의 정보 저장
        // 로그인한 사람의 정보를 누가 기억하냐 서버가 기억한다 ? 세션 클라이언트가 저장한다 ? 쿠키
        session.setAttribute("login", dto);
        // 세션도 수명을 설정해야 함.
        session.setMaxInactiveInterval(60 * 60); // 1시간이 지나면 로그인이 풀림
    }

}
