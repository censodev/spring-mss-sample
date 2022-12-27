package io.github.censodev.serviceauth.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInReq {
    private String username;
    private String password;
}
