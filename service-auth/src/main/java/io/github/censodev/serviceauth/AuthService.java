package io.github.censodev.serviceauth;

import io.github.censodev.commonsapi.auth.RoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

public interface AuthService {
    @Getter
    @Builder
    class Tokens {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Setter
    class SignInReq {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    class SignUpReq {
        private String username;
        private String password;
        private Collection<RoleEnum> roles;
    }

    Tokens signin(SignInReq signInReq);

    void signup(SignUpReq signUpReq);
}
