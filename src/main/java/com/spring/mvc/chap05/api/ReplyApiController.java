package com.spring.mvc.chap05.api;


import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.dto.request.ReplyModifyRequestDTO;
import com.spring.mvc.chap05.dto.request.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    // 댓글 등록 요청 처리
    @PostMapping
    public ResponseEntity<?> create(
            @Validated // 검증 하겠다
            @RequestBody ReplyPostRequestDTO dto
            , BindingResult result // 검증 결과 메세지를 가진 객체
    ){

        // 입력값 검증에 걸리면 400번 코드와 함께 메시지를 클라이언트에 전송 하겠다.
        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        log.info("/api/v1/replies : POST");
        log.debug("request : parameter : {}", dto);

        try {
            ReplyListResponseDTO responseDTO = replyService.register(dto);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (SQLException e) {
            log.warn("500 status code response!! caused by {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    //댓글 삭제 요청 처리
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?>  remove(
            @PathVariable Long replyNo
    ){
      if(replyNo == null){
          return ResponseEntity
                  .badRequest()
                  .body("댓글 번호를 보내주세요!");
      }

      log.info("/api/v1/replies/{} : DELETE", replyNo);

      try {
          ReplyListResponseDTO delete = replyService.delete(replyNo);
          return ResponseEntity
                  .ok()
                  .body(delete)
                  ;
      }catch (Exception e){
          return ResponseEntity
                  .internalServerError()
                  .body(e.getMessage())
                  ;
      }
    }

    //댓글 수정 요청 처리
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> update(
            @Validated
            @RequestBody
            ReplyModifyRequestDTO dto
            , BindingResult result
    ) {

        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(result.toString())
                    ;
        }

        log.info("/api/v1/replies PUT/PATCH {}");
        log.debug("parameter : {}", dto);

        try{
            ReplyListResponseDTO modify = replyService.modify(dto);
            return ResponseEntity
                    .ok()
                    .body(modify)
                    ;
        }catch (Exception e){
            log.warn("internal server error! caused by : {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage())
                    ;
        }
    }

}
