package com.brainstorm.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF (not recommended for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/orders/createOrder").hasRole("ADMIN")  // ADMIN role for createOrder
                        .requestMatchers("/api/orders/fetchOrder").hasAnyRole("USER", "ADMIN")  // USER or ADMIN for fetchOrder
                        .requestMatchers("/api/orders/java-version").hasRole("ADMIN")
                        .requestMatchers("/api/orders/contact-info").hasRole("ADMIN")
                        .requestMatchers("/api/orders/build-info").hasRole("ADMIN")
                        .requestMatchers("/api/orders/deleteOrder").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});
        return http.build();
    }

}
