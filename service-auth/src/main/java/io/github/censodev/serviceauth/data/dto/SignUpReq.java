package io.github.censodev.serviceauth.data.dto;

import io.github.censodev.serviceauth.data.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class SignUpReq {
    private String username;
    private String password;
    private Collection<RoleEnum> roles;
}
