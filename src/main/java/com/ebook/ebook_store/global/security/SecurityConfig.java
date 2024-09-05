package com.ebook.ebook_store.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests.requestMatchers(
                                        "/h2-console/**")
                                .permitAll()
                )


                .csrf(
                        csrf -> csrf
                                .ignoringRequestMatchers("/h2-console/**")
                                .ignoringRequestMatchers(
                                        PathRequest.toH2Console() + "/**"
                                )

                )
                .headers(
                        headers ->
                                headers.frameOptions(
                                        frameOptions ->
                                                frameOptions.sameOrigin()
                                )
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/member/login")

                );
        return http.build();
    }


}
