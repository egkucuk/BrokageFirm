package com.springboot.brokagefirm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/orders").authenticated() // Create Order
                        .requestMatchers(HttpMethod.GET, "/orders").authenticated() // List Orders
                        .requestMatchers(HttpMethod.DELETE, "/orders/**").authenticated() // Delete Order
                        .requestMatchers(HttpMethod.POST, "/customers/deposit").authenticated() // Deposit Money
                        .requestMatchers(HttpMethod.POST, "/customers/withdraw").authenticated() // Withdraw Money
                        .requestMatchers(HttpMethod.GET, "/customers/**/assets").authenticated() // List Assets
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Admin-only endpoints
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults()); // Enable basic authentication

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
