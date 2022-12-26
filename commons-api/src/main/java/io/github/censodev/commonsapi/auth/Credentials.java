package io.github.censodev.commonsapi.auth;

import io.github.censodev.jwtprovider.CanAuth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Credentials implements CanAuth {
    protected String username;
    protected String password;
    protected Collection<RoleEnum> roles;

    @Override
    public Object subject() {
        return username;
    }

    @Override
    public Collection<String> authorities() {
        return roles.stream().map(RoleEnum::name).toList();
    }
}
