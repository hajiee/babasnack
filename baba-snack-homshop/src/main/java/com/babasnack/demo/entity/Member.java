package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private String username;
    private String password;
    @Builder.Default
    private Long pnoTell = 0L;
    private String email;
    private LocalDate joinDay;
    private String role;
}