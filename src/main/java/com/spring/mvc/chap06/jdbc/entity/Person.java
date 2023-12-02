package com.spring.mvc.chap06.jdbc.entity;


import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;
    private String personName;
    private int personAge;
}
