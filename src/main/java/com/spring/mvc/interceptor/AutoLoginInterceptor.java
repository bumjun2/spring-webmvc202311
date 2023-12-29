package com.spring.mvc.interceptor;

import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import com.spring.mvc.chap05.service.MemberService;
import com.spring.mvc.util.LoginUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {
    private final MemberMapper mapper;
    private final MemberService service;
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        // 1. 사이트에 들어오면 자동로그인 쿠키를 가진 클라이언트인지 체크
        Cookie autoLoginCookie = WebUtils.getCookie(request, LoginUtils.AUTO_LOGIN_COOKIE);

        // 2. 자동 로그인 쿠키가 있다면 로그인 처리를 수행한다
        if (autoLoginCookie != null){

            // 3. 지금 읽은 쿠키에 들어있는 세션 아이디 확인
            String sessionId = autoLoginCookie.getValue();
            // 4. db에서 쿠키에 들어있는 세션 아이디를 가진 회원을 조회한다.
            Member member = mapper.findMemberByCookie(sessionId);
            // 5. 회원이 정상 조회되고 자동로그인 만료시간 이전이면 로그인을 수행해준다
            if(member != null && LocalDateTime.now().isBefore(member.getLimitTime())){
                service.maintainLoginState(
                        request.getSession(),
                        member.getAccount()
                );
            }
        }
        return true;
    }
}
