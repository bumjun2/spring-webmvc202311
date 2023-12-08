package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardJdbcRepository;
import com.spring.mvc.chap05.repository.BoardRepository;
import com.spring.mvc.chap05.repository.BoardRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository repository;

    public BoardController(@Qualifier("db") BoardRepository repository) {
        this.repository = repository;
    }

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Model model){
        List<Board> boardList = repository.findAll();
        model.addAttribute("bList", boardList);
        return "chap05/list";
    }
    // 2. 글쓰기 화면요청 (/board/write : GET)
    @GetMapping("/write")
    public String write(){
        return "chap05/write";
    }
    // 3. 글쓰기 등록요청 (/board/write : POST)
    @PostMapping("/write")
    public String reWrite(String title, String content, Model model){
        Board b = new Board(4, title, content);
        repository.save(b);
        List<Board> boardList = repository.findAll();

        model.addAttribute("bList", boardList);
        return "redirect:/board/list";
    }
    // 4. 글 삭제 요청 (/board/delete : GET)
    @GetMapping("/delete")
    public String delete(@RequestParam int bno){
        repository.delete(bno);

        return "redirect:/board/list";
    }
    // 5. 글 상세보기 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(int bno, Model model){
        Board b = repository.findOne(bno);
        BoardRequestDTO b2 = new BoardRequestDTO(b);
        model.addAttribute("b", b2);
        return "chap05/detail";
    }
}
