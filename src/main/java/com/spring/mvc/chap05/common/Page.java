package com.spring.mvc.chap05.common;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Page {
    private int pageNo; // 클라이언트가 보낸 페이지 번호
    private int amount; // 클라이언트가 보낸 목록게시물 수

    public Page() {
        this.pageNo = 1;
        this.amount = 6;
    }

    /*
        만약에 한 페이지에 게시물을 10개씩 뿌린다고 가정하면

        1페이지 -> limit 0,10
     */

    public int getPageStart() {
        return (pageNo - 1 ) * amount;
    }
}
