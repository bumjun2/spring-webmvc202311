package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.service.SnsLoginService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SnsLoginController {

    private final SnsLoginService service;

    @Value("${sns.kakao.app-key}")
    private String kakaoAppkey;

    @Value("${sns.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    // 카카오 인가 코드 발급 요청
    @GetMapping("/kakao/login")
    public String kakaoLogin(){
        String uri = "https://kauth.kakao.com/oauth/authorize";
        uri += "?client_id=" + kakaoAppkey;
        uri += "&redirect_uri=" + kakaoRedirectUri;
        uri += "&response_type=code";
        return "redirect:" + uri;
    }

    // 인가 코드 받기
    @GetMapping("/auth/kakao")
    public String snsKakao(String code, HttpSession session){
        log.info(
                "카카오 로그인 코드 : {}", code
        );

        // 인가 코드를 가지고 카카오 인증 서버에 토큰 발급 요청을 보냄

        // server to server 통신
        HashMap<String, String> params = new HashMap<>();
        params.put("appkey", kakaoAppkey);
        params.put("redirect", kakaoRedirectUri);
        params.put("code", code);
        service.kakaoLogin(
                params,
                session
        );

        return "redirect:/";
    }

}
