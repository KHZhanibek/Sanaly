package com.sanalyapp.sanaly.jwt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class JwtAuthenticationRequest {
    private String email, password;
}
