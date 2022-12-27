package io.github.censodev.serviceauth.web.controllers;

import io.github.censodev.serviceauth.data.domains.User;
import io.github.censodev.serviceauth.data.dto.SignInReq;
import io.github.censodev.serviceauth.data.dto.SignUpReq;
import io.github.censodev.serviceauth.data.dto.Tokens;
import io.github.censodev.serviceauth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("profile")
    public User profile(Authentication authentication) {
        return (User) authentication.getCredentials();
    }

    @GetMapping("login")
    public Tokens login(@RequestBody SignInReq signInReq) {
        return authService.signin(signInReq);
    }

    @GetMapping("signup")
    public void signup(@RequestBody SignUpReq signUpReq) {
        authService.signup(signUpReq);
    }
}
