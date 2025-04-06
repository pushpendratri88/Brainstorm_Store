package com.brainstorm.customer.config;

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
                        .requestMatchers("/api/customers/newCustomer").hasRole("ADMIN") // ADMIN role for newCustomer
                        .requestMatchers("/api/customers/customerRegistration").hasRole("ADMIN")  // ADMIN role for customerRegistration
                        .requestMatchers("/api/customers/removeCustomer").hasRole("ADMIN")  // ADMIN role for removeCustomer
                        .requestMatchers("/api/customers/getCustomer").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/customers/getCustomerByEmail").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/customers/updateCustomerDetails").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/customers/java-version").hasRole("ADMIN")
                        .requestMatchers("/api/customers/contact-info").hasRole("ADMIN")
                        .requestMatchers("/api/customers/build-info").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});
        return http.build();
    }
}
