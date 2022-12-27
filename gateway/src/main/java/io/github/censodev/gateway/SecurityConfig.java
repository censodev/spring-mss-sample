package io.github.censodev.gateway;

import io.github.censodev.jwtprovider.CanAuth;
import io.github.censodev.jwtprovider.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Slf4j
public class SecurityConfig {
    @Value("${auth.secret}")
    private String authSecret;

    @Bean
    public JwtProvider tokenProvider() {
        return JwtProvider.secret(authSecret).build();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .csrf()
                .disable()
                .cors()
                .and()
                .addFilterBefore(new AuthFilter<>(tokenProvider(), CanAuth.class), SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/api/auth/login", "/api/auth/signup").permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(withDefaults())
                .build();
    }
}
