package com.spring.mvc.interceptor;


import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.util.LoginUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
    - 인테셉터 : 컨트롤러에 요청이 들어가기 전 후에 공통적으로 처리할
               코드나 검사할 일들을 정의해 놓은 클래스
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardMapper mapper;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        // 로그인을 안 했으면 글쓰기, 글 수정 글삭제 튕겨낼 것
        HttpSession session = request.getSession();

        if (!LoginUtils.isLogin(session)){
            log.info("this request ({}) is denied !! ", request.getRequestURI());
            response.sendRedirect("/members/sign-in");
            return false;
        }

        //쿼리스트링에서 구함 ? 뒤에 붙어있음
        String bno = request.getParameter("bno");


        // 삭제 요청이 들어올때 서버에서 관리자인지 내가 쓴 글인지 확인

        //현재 요청이 삭제 요청인지 확인
        String uri = request.getRequestURI();

        if (uri.contains("delete")){
            //로그인한 계정명과 게시물의 겨정명의 일치하는지 체크
            String targetAccount = mapper.findOne(Integer.parseInt(bno)).getAccount();

            if (LoginUtils.isAdmin(session)) return true;

            //만약에 내가 쓴 글이 아니면  접근 권한이 없다는 안내페이지로 이동
            if(!LoginUtils.isMine(session, targetAccount)){
                response.sendRedirect("/access-deny");
                return false;
            }
        }
        return true;
    }
}
