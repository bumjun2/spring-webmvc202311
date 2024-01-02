package com.spring.mvc.chap05.service;


import com.spring.mvc.chap05.dto.request.AutoLoginDTO;
import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SinUpRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import com.spring.mvc.util.LoginUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper mapper;
    private final PasswordEncoder encoder;

    // 회원가입 처리 서비스
    public boolean join(SinUpRequestDTO dto, String savePath) {
        // 클라이언트가 보낸 회원가입 데이터를
        // 패스워드 인코딩하여 엔터티로 변환해서 전달

        return mapper.save(dto.toEntity(encoder, savePath));
    }

    // 로그인 검증 처리
    public LoginResults authenticate(
            LoginRequestDTO dto,
            HttpSession session,
            HttpServletResponse response
    ) {

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

        // 자동 로그인 처리
        if (dto.isAutoLogin()){
            // 자동 로그인 쿠키 생성 - 쿠키 안에 절대 중복되지 않은 값(브라우저 세션 아이디)을 저장
            Cookie autoLoginCookie = new Cookie(LoginUtils.AUTO_LOGIN_COOKIE, session.getId());

            // 2. 쿠키 설정 - 사용 경로, 수명 ....
            int limitTime = 60 * 60 * 24 * 90; // 자동 로그인 유지 시간

            autoLoginCookie.setPath("/");
            autoLoginCookie.setMaxAge(limitTime);

            // 3. 쿠키를 클라이언트에 전송
            response.addCookie(autoLoginCookie);

            // 4. db에도 쿠키에 관련된 값들을(랜덤 세션 키, 만료 시간) 저장
            mapper.saveAutoLogin(
                    AutoLoginDTO.builder()
                            .account(dto.getAccount())
                            .sessionId(session.getId())
                            .limitTime(LocalDateTime.now().plusDays(90))
                            .build()
            );


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
                .auth(member.getAuth().name())
                .profile(member.getProfileImage())
                .build();

        // 세션에 로그인한 회원의 정보 저장
        // 로그인한 사람의 정보를 누가 기억하냐 서버가 기억한다 ? 세션 클라이언트가 저장한다 ? 쿠키
        session.setAttribute(LoginUtils.LOGIN_KEY, dto);
        // 세션도 수명을 설정해야 함.
        session.setMaxInactiveInterval(60 * 60); // 1시간이 지나면 로그인이 풀림
    }

    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {
        // 1. 자동 로그인 키를 가져온다
        Cookie c = WebUtils.getCookie(request, LoginUtils.AUTO_LOGIN_COOKIE);
        // 2. 쿠키를 삭제한다
        // -> 쿠키의 수명을 0초로 설정하여 다시 클라이언트에 전송
        if (c != null){
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);

            // 3. 데이터베이스도 셔션아이디와 만료시간을 제거한다
            mapper.saveAutoLogin(
                    AutoLoginDTO.builder()
                            .sessionId(null)
                            .limitTime(LocalDateTime.now())
                            .account(LoginUtils.getCurrentLoginMemberAccount(request.getSession()))
                            .build()
            );
        }
    }
}
