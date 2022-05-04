package com.sanalyapp.sanaly.security;

import com.sanalyapp.sanaly.jwt.JwtAuthenticationFilter;
import com.sanalyapp.sanaly.jwt.JwtAuthenticationRequest;
import com.sanalyapp.sanaly.jwt.JwtTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(STATELESS)
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilterAfter(new JwtTokenVerifier(), JwtAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/register", "/api/register/**", "/login").permitAll()
                .anyRequest().permitAll();
    }
}
