package com.rentalplatform.equipmentrental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/login.html",
                                "/register.html",
                                "/products.html",
                                "/cart.html",
                                "/orders.html",
                                "/checkout-success.html",  // ← Al toegevoegd
                                "/profile.html",
                                "/admin-dashboard.html",
                                "/admin-orders.html",
                                "/styles.css",
                                "/app.js",
                                "/api/auth/**",
                                "/api/products/**",
                                "/api/categories/**",
                                "/api/cart/**",
                                "/api/rentals/**",
                                "/h2-console/**",
                                "/**/*.css",
                                "/**/*.js"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}