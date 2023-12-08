package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("db")
@Primary
@RequiredArgsConstructor
public class BoardJdbcRepository implements BoardRepository{

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board ORDER BY reg_date_time DESC";
        return template.query(sql, (rs, rowNum) -> new Board(rs));
    }

    @Override
    public boolean save(Board board) {
        String sql = "INSERT INTO tbl_board " +
                "(title, content) " +
                "VALUES (?, ?)";
        return template.update(sql, board.getTitle(), board.getShortContent()) == 1;
    }

    @Override
    public boolean delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        return template.update(sql, boardNo) == 1;
    }

    @Override
    public Board findOne(int bno) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        viewCount(bno);
        return template.queryForObject(sql, (rs, rn) -> new Board(rs), bno);
    }

    public void viewCount(int no){
        String sql = "UPDATE tbl_board SET view_count = view_count +1 WHERE board_no = ?";
        template.update(sql, no);
    }
}
