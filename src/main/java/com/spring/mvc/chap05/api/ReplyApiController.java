package com.spring.mvc.chap05.api;


import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/replies")
public class ReplyApiController {
    private final ReplyService replyService;
    //댓글 목록 조회 요청
    //규칙 리소스는 복수형으로 써라
    //url : /api/v1/replies/글번호/page/페이지번호
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable long boardNo,
            @PathVariable int pageNo
            ){
        log.info("/api/v1/replies/{}/page/{} GET", boardNo, pageNo);

        Page page = new Page();

        page.setPageNo(pageNo);
        page.setAmount(5);

        ReplyListResponseDTO list = replyService.getList(boardNo, page);
        return ResponseEntity
                .ok()
                .body(list)
                ;
    }
}
