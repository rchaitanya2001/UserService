package org.example.userservice.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        try {
//            http
//                    .authorizeHttpRequests((requests) ->{
//                            try
//                            {
//                                requests
//                                        .anyRequest().permitAll()
//                                        .and().cors().disable()
//                                        .csrf().disable();
//                            }
//                            catch (Exception e)
//                            {
//                                throw new RuntimeException(e);
//                            }
//
//
//        }
//        return http.build();
//
//
//
//
//}
