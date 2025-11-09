package com.example.alimentos;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/home", "/login","/recetas/buscar","/buscar", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true) 
                .failureUrl("/login?error=true")
                .permitAll()
            );

            http
                .headers(headers -> headers
                    .contentSecurityPolicy(csp -> csp
                        .policyDirectives("default-src 'self'; " +
                                        "style-src 'self' https://fonts.googleapis.com 'unsafe-inline'; " +
                                        "font-src 'self' https://fonts.gstatic.com; " +
                                        "img-src 'self' data:; " +
                                        "script-src 'self';")
                    )
                );

        return http.build();
    }

}
