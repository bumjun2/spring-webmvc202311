package com.spring.mvc.chap05.common;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString
public class PageMaker {

    // 페이지 첫 번호랑 끝번호 (1 ~ 10, 11 ~ 20)
    private int begin, end, finalPage; // finalPage : 보정된 end
    // 이전 다음 버튼 활성화 여부
    private boolean prev, next;

    //현재 페이지 정보
    private Page page;

    // 총 게시물 수
    private int totalCount;

    public PageMaker(Page page, int totalCount){
        this.page = page;
        this.totalCount = totalCount;

        makePageInfo();
    }

    // 한 화면에 보여질 페이지 수
    public static final int Page_COUNT = 10;


    //페이지 생성에 필요한 데이터를 만드는 알고리즘
    private void makePageInfo(){
        // 1. end값 계산
        /*
            지금 사용자가 7페이지에 있다
            -> 1 ~ 10구간을 만들어야 함
            지금 사용자가 24페이지에 있다
            -> 20 ~ 30구간을 만들어야 함
         */
        // 공식 : (올림(현재 사용자가 위치한 페이지 넘버 / 한 화면에 보여줄 페이지 수)) * 한 화면에 보여줄 페이지 수
        this.end = (int) (Math.ceil((double) page.getPageNo() / Page_COUNT) * Page_COUNT);
        // 2. begin값 구하기
        this.begin = end - Page_COUNT + 1;

        // prev 활성화
        this.prev = begin > 1;

        /*
            end 값 고정
            - 마지막 구간 보정 공식
            올림 (총 게시물 수 / 한 페이지에 배치할 게시물의 수)
         */

        this.finalPage = (int) Math.ceil((double) totalCount / page.getAmount());
        // 마지막 페이지 구간에서 end값을 finalPage값으로 변경
        if(this.finalPage < this.end) this.end = this.finalPage;
        // next 활성화 여부
        this.next = this.end < this.finalPage;
    }
}
