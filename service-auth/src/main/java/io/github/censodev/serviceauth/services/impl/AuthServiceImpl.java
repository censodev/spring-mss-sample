package io.github.censodev.serviceauth.services.impl;

import io.github.censodev.serviceauth.data.enums.RoleEnum;
import io.github.censodev.jwtprovider.JwtProvider;
import io.github.censodev.serviceauth.data.domains.User;
import io.github.censodev.serviceauth.data.dto.SignInReq;
import io.github.censodev.serviceauth.data.dto.SignUpReq;
import io.github.censodev.serviceauth.data.dto.Tokens;
import io.github.censodev.serviceauth.data.repos.UserRepository;
import io.github.censodev.serviceauth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider tokenProvider;

    @Override
    public Tokens signin(SignInReq signInReq) {
        return userRepository.findByUsername(signInReq.getUsername())
                .filter(u -> passwordEncoder.matches(signInReq.getPassword(), u.getPassword()))
                .map(u -> Tokens.builder()
                        .accessToken(tokenProvider.generate(u))
                        .refreshToken(tokenProvider.generate(u, 86_400_000))
                        .build())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

    @Override
    public void signup(SignUpReq signUpReq) {
        userRepository.save(User.builder()
                .username(signUpReq.getUsername())
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .roles(Optional.ofNullable(signUpReq.getRoles())
                        .orElse(List.of(
                                RoleEnum.ROLE_ADMIN,
                                RoleEnum.ROLE_CUSTOMER
                        )))
                .build());
    }
}
