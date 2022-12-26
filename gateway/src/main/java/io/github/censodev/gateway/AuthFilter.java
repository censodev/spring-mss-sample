package io.github.censodev.gateway;

import io.github.censodev.jwtprovider.CanAuth;
import io.github.censodev.jwtprovider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class AuthFilter<T extends CanAuth> implements WebFilter, Ordered {
    private final JwtProvider tokenProvider;
    private final Class<T> canAuthConcreteClass;

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        try {
            String token = header.replace("Bearer ", "");
            tokenProvider.verify(token);
            T canAuthConcrete = tokenProvider.getCredential(token, canAuthConcreteClass);
            List<SimpleGrantedAuthority> authorities = canAuthConcrete
                    .authorities()
                    .stream().map(SimpleGrantedAuthority::new)
                    .toList();
            Object principle = canAuthConcrete.subject();
            Authentication auth = new UsernamePasswordAuthenticationToken(principle, canAuthConcrete, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
        return chain.filter(exchange);
    }
}
