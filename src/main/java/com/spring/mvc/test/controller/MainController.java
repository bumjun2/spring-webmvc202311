package com.spring.mvc.test.controller;

import com.spring.mvc.test.dto.MemberRequestDTO;
import com.spring.mvc.test.dto.MemberResponseDto;
import com.spring.mvc.test.entity.Member;
import com.spring.mvc.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {
    private final MemberService service;

    @GetMapping("")
    public String main(Model model) {
        List<MemberResponseDto> dtoList = service.memberAll();
        model.addAttribute("mList", dtoList);
        return "review/index";
    }

    @PostMapping("/search")
    public String find(String id, Model model){
        Member member = service.memberOne(id);
        model.addAttribute("m", member);
        return "review/list";
    }


    @GetMapping("/join")
    public String join(){
        return "review/join";
    }

    @PostMapping("/join/ok")
    public String ok(Member member){
        System.out.println("welcom join ok");
        service.newMember(member);
        return "redirect:/main";
    }

    @PostMapping("/isLogin")
    public String isLogin(MemberRequestDTO dto, Model model){
        System.out.println("welcom to isLogin");
        String message = service.login(dto);
        System.out.println("message = " + message);
        
        model.addAttribute("message", message);
        return "review/login";
    }

    @GetMapping("/delete")
    public String delete(String id){
        System.out.println("id = " + id);
        service.remove(id);
        return "redirect:/main";
    }
}
