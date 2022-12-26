package io.github.censodev.serviceauth;

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
    public AuthService.Tokens login(@RequestBody AuthService.SignInReq signInReq) {
        return authService.signin(signInReq);
    }

    @GetMapping("signup")
    public void signup(@RequestBody AuthService.SignUpReq signUpReq) {
        authService.signup(signUpReq);
    }
}
