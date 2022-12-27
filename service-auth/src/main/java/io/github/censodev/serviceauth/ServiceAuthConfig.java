package io.github.censodev.serviceauth;

import io.github.censodev.jwtprovider.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceAuthConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProvider jwtProvider(@Value("${auth.secret}") String secret) {
        return JwtProvider.secret(secret).build();
    }
}
