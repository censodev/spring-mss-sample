package io.github.censodev.serviceauth.services;

import io.github.censodev.serviceauth.data.dto.SignInReq;
import io.github.censodev.serviceauth.data.dto.SignUpReq;
import io.github.censodev.serviceauth.data.dto.Tokens;

public interface AuthService {
    Tokens signin(SignInReq signInReq);

    void signup(SignUpReq signUpReq);
}
