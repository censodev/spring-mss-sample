package io.github.censodev.serviceauth.data.domains;

import io.github.censodev.serviceauth.data.enums.RoleEnum;
import io.github.censodev.jwtprovider.CanAuth;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User implements CanAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    private Collection<RoleEnum> roles;

    @Override
    public Object subject() {
        return id;
    }

    @Override
    public Collection<String> authorities() {
        return roles.stream().map(RoleEnum::name).toList();
    }
}
