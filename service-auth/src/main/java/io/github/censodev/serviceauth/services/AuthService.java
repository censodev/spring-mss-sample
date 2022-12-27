package io.github.censodev.serviceauth.services;

import io.github.censodev.serviceauth.api.dto.SignInReq;
import io.github.censodev.serviceauth.api.dto.SignUpReq;
import io.github.censodev.serviceauth.api.dto.Tokens;

public interface AuthService {
    Tokens signin(SignInReq signInReq);

    void signup(SignUpReq signUpReq);
}
