package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BoardRepositoryImpl implements BoardRepository{
    private static final Map<Integer, Board> boardMap;
    static {
        boardMap = new HashMap<>();
        Board b1 = new Board(1, "이마트 영업시", "10시에 마감하는걸로 바뀌었나요? 마감 털이 몇시에 가야되죠 ? 하....", 0, LocalDateTime.now());
        Board b2 = new Board(2, "관종의 조건", "이 세상은 나를 중심으로 돌아간다 라는 마음으로 행동해라...", 0, LocalDateTime.now());
        Board b3 = new Board(3, "돈까스 레시피", "그냥 이마트에서 사서 에어프라이 돌려라~", 0, LocalDateTime.now());

        boardMap.put(b1.getBoardNo(), b1);
        boardMap.put(b2.getBoardNo(), b2);
        boardMap.put(b3.getBoardNo(), b3);
    }

    @Override
    public List<Board> findAll() {
//        List<Board> temp = new ArrayList<>();
//        for (Integer key : boardMap.keySet()) {
//            Board board = boardMap.get(key);
//            temp.add(board);
//        }
        return new ArrayList<>(boardMap.values())
                .stream()
                .sorted(Comparator.comparing(s -> s.getBoardNo()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Board board) {
        boardMap.put(board.getBoardNo(), board);
        return true;
    }

    @Override
    public boolean delete(int bno) {
        boardMap.remove(bno);
        return true;
    }

    @Override
    public Board findOne(int stuNum) {
        return boardMap.get(stuNum);
    }

}
