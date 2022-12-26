package io.github.censodev.commonsapi.auth;

import io.github.censodev.jwtprovider.CanAuth;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class Credentials implements CanAuth {
    private String username;
    private List<RoleEnum> roles;

    @Override
    public Object subject() {
        return username;
    }

    @Override
    public Collection<String> authorities() {
        return roles.stream().map(RoleEnum::name).toList();
    }
}
