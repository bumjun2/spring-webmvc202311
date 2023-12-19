package com.spring.mvc;


import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.chap05.repository.BoardRepositoryImpl;
import com.spring.mvc.chap05.repository.ReplyMapper;
import com.spring.mvc.chap06.jdbc.entity.Person;
import com.spring.mvc.chap06.mybatis.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/rests")
@RequiredArgsConstructor
@Slf4j
public class RestApiController {
    private final ReplyMapper replyMapper;

    @GetMapping("/hello")
//    @ResponseBody // 클라이언트에게 jsp를 보내는게 아니라 json을 보내는 방법
    public String hello(){
        log.info("/rest/hello GET");
        return "hello apple banana!";
    }

    @GetMapping("/food")
//    @ResponseBody
    public List<String> food(){
        return List.of("짜장면", "볶음밥", "탕수육");
    }


    @GetMapping("/person-list")
    public ResponseEntity<?> personList(){
        List<Person> personList = List.of(
                new Person("111", "딸기 공듀", 30),
                new Person("222", "잔망 공듀", 45),
                new Person("333", "뽀로 공듀", 55)
        );
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("my-pet", "냥냥이");
        return ResponseEntity
                .ok()
//                .headers(headers)
                .body(personList);
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg){
        if (cm == null || kg == null){
            return ResponseEntity
                    .badRequest()
                    .body("시발 뭐하냐");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits", "melon");
        headers.add("pet", "dog");

        double bmi = kg / ((cm / 100) * (cm / 100));
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bmi);
    }
}
