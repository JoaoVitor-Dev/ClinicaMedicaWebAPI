package com.joaovitor.clinicamedicawebapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // libera tudo
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            // desabilita CSRF (necessário para POST/PUT/DELETE sem auth)
            .csrf(csrf -> csrf.disable())
            // desabilita login padrão
            .formLogin(form -> form.disable())
            // desabilita logout
            .logout(logout -> logout.disable());

        return http.build();
    }
}

