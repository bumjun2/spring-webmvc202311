package com.spring.mvc.chap05.controller;


import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SinUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResults;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    //회원가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp(){
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
        return "members/sign-up";
    }


    // 아이디 이메일 중복체크 비동기 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity check(String type, String keyword){
        log.info("/members/check welcome to bumjun !!");
        boolean flag = service.checkDuplicateValue(type, keyword);
        log.debug("중복 체크 결과 : {}", flag);
        return ResponseEntity
                .ok()
                .body(flag);
    }

    @PostMapping("sign-up")
    public String signUp(SinUpRequestDTO dto){
        log.info("{}", dto);

        boolean flag = service.join(dto);
        return flag ? "redirect:/board/list" : "redirect:/members/sign-up";
    }


    //로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn(HttpSession session){

//        if(session.getAttribute("login") != null){
//            return "redirect:/";
//        }

        log.info("/members/sign-in GET - forwarding to sign-in.jsp");



        return "members/sign-in";
    }


    //로그인 검증
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto
                    // 모델에 담긴 데이터는 리다이렉트시 jsp가지 않는다.
                    // 왜냐면 리다이렉트는 요청이 2번들어가서 첫번째요청시 보관한 데이터가 소실된다.
            , RedirectAttributes ra
            , HttpServletResponse response
            , HttpServletRequest request
    ){

        log.info("members/sign-in POST");
        log.debug("{}", dto);

        LoginResults result = service.authenticate(dto);
        log.debug("login result : {}", result);

        ra.addFlashAttribute("msg", result);

        if(result == LoginResults.SUCCESS){
//            makeLoginCookie(dto, response); // 쿠키로 로그인 유지

            // 세션으로 로그인 유지
            service.maintainLoginState(request.getSession(), dto.getAccount());

            return "redirect:/";
        }

        return "redirect:/members/sign-in";
    }

    // 로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(
            HttpServletRequest request
    ){
        HttpSession session = request.getSession();
        // 세션에서 로그인 정보 기록 삭제
        session.removeAttribute("login");
        // 세션을 초기화 (RESET)
        session.invalidate();

        return "redirect:/";
    }



    private static void makeLoginCookie(LoginRequestDTO dto, HttpServletResponse response) {
        //쿠키에 로그인 기록을 저장
        Cookie cookie = new Cookie("login", dto.getAccount());

        cookie.setPath("/"); // 이 쿠키는 모든 경로에서 들고 다녀야 함
        cookie.setMaxAge(60); // 쿠크 수명 설정

        // 쿠키를 클라이언트에게 전송 (Response객체 필요)
        response.addCookie(cookie);
    }
}
