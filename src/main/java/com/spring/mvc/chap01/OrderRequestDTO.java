package com.spring.mvc.chap01;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderRequestDTO {
    // 클라이언트가 보내는 파라미터의 이름을 필드로 똑같이 구성한다.
    // setter와 기본생성자와 반드시 있어야 한다.
    private String orderNum;
    private String goodsName;
    private int amount;
    private int price;

}
