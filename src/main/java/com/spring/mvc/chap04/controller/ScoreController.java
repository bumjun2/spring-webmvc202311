package com.spring.mvc.chap04.controller;

/*
    # 컨트롤러
    - 클라이언트의 요청(request)을 받아서 응답(response)을 제공하는 객체
    # 요청 URL Endpoint

    1. 학생의 성적정보 등록폼 화면을 보여주고
       동시에 지금까지 저장되어 있는 성적 정보 목록을 조회
    - /score/list : GET (조회는 GET방식)

    2. 학생의 입력된 성적정보를 데이터베이스에 저장하는 요청
    - /score/register : POST

    3. 성적정보를 삭제 요청
    - /score/remove : GET or POST

    4. 성적정보 상세 조회 요청
    - /score/detail : GET
 */

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller()
@RequestMapping("/score")
@RequiredArgsConstructor // fianl이 붙은 필드를 초기화하는 생성자를 생성
//@AllArgsConstructor // 모든 필드를 초기화하는 생성자를 생성
public class ScoreController {

    // 저장속에 의존하여 데이터 처리를 위임한다.
    private final ScoreRepository repository;

    // @Autowired -> 스프링에 등록된 빈을 자동주입
    // 생성자 주입을 사용하고 생성자가 단 하나 -> autowired생략가능
//    public ScoreController(ScoreRepository repository) {
//        this.repository = repository;
//    }

    // 1. 성적 폼 띄위기 + 목록조회
    // - jsp파일로 입력폼 화면을 띄워줘야 함 (view 포워딩)
    // - 저장된 성적정보 리스트를 jsp에 보내줘야 함 (modal에 데이터 전송)
    // - 저장된 성적정보 리스트를 어떻게 가져오느냐
    @GetMapping("/list")
    public String list(Model model){
        System.out.println("/score/list GET");
        List<Score> scoreList = repository.findAll();
        System.out.println(scoreList);
        model.addAttribute("sList", scoreList);
        return "chap04/Score-list";
    }

    // 2. 성적정보를 데이터베이스에 저장하는 요청

    @PostMapping("/register")
    public String register(ScoreRequestDTO score){
        System.out.println("/score/register POST!!");
        System.out.println(score);

        // DTO를 엔터티로 변환 -> 데이터 생성
        Score savedScore = new Score(score);
        repository.save(savedScore);

        /*
            #forwoard vs redirect
            - 포워드는 여청 리소스를 그대로 전달해줌.
            - 따라서 url이 변경되지 않고 한번의 요청과 한번의 응답이 이뤄짐
            - 리다이렉트는 요청후에 자동응답이 나가고
            2번째 자동요청이 들어오면서, 2번째 응답을 내보냄
            -따라서 2번째  요청의 url로 자동 변경됨
        */
        //forward할때는 포워딩할 파일의 경로를 적는다

        //redirect할때는 리다이엑트 요청 url을 적는것
        // ex) http://localhost:8080/score/detail
        return "redirect:/score/list";
    }
    // 3. 성적 삭제 요청
    @RequestMapping(value="/remove/{stuNum}", method = {RequestMethod.GET, RequestMethod.POST}) // GET, POST둘다 받는 방법
    public String remove(HttpServletRequest request,
                         @PathVariable int stuNum){
        System.out.printf("/score/remove %s\n", request.getMethod());
        System.out.println(stuNum);

        repository.delete(stuNum);

        return "redirect:/score/list";
    }
    // 4. 성적 상세 조회 요청
    @GetMapping("/detail")
    public String detail(){
        System.out.println("/score/detail GET");
        return "";
    }
}