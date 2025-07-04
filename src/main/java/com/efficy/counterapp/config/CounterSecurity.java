package com.efficy.counterapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CounterSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/v1/api/product/get").authenticated()
                        .requestMatchers("/v1/api/product/**").permitAll() // Allow other product APIs
                        .anyRequest().denyAll() // block everything else (optional)
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(flc->flc.disable());

        return http.build();
    }
}
