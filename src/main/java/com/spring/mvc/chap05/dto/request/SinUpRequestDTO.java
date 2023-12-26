package com.spring.mvc.chap05.dto.request;


import com.spring.mvc.chap05.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SinUpRequestDTO {
    @NotBlank
    @Size(min = 4, max = 14)
    private String account;
    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 2, max = 6)
    private String name;
    @NotBlank
    @Email
    private String email;


    public Member toEntity(PasswordEncoder encoder) {
        return Member.builder()
                .account(this.account)
                .password(encoder.encode(password))
                .email(this.email)
                .name(this.name)
                .build()
                ;
    }
}
