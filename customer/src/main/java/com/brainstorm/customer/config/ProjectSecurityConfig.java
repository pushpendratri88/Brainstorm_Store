package com.brainstorm.customer.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers(
                "/api/customers/newCustomer",
                        "/api/customers/create",
                        "/api/customers/fetchAllCustomers",
                        "/api/customers/fetchCustomerDetails",
                        "/api/customers/fetchCustomerDetailsWithEmail",
                        "/api/customers/update","/api/customers/remove").authenticated()
                .requestMatchers(
                        "/api/customers/build-info",
                        "/api/customers/java-version",
                        "/api/customers/contact-info",
                        "/api/customers/error").permitAll());

//        http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable()); // Use if you want to disable Form Login.
//        http.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable()); // Use if you want to disable HTTP Basic
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}
