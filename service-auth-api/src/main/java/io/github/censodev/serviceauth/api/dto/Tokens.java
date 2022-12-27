package io.github.censodev.serviceauth.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tokens {
    private String accessToken;
    private String refreshToken;
}
