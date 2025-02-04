package com.sahil.stock.portfolio.security;

import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sahil.stock.portfolio.exception.MissingRefreshTokenException;
import com.sahil.stock.portfolio.exception.UserNotFoundException;
import com.sahil.stock.portfolio.repository.UserRepository;
import com.sahil.stock.portfolio.service.AuthService;
import com.sahil.stock.portfolio.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String refreshToken = request.getHeader("Refresh-Token");
        final String authorization = request.getHeader("Authorization");

        if (refreshToken == null) {
            throw new MissingRefreshTokenException("Refresh token is required");
        }

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorization.substring(7);
        final String email = jwtService.extractClaim(token, Claims::getSubject);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserPrincipal userPrincipal = userRepository
                    .findByEmail(email)
                    .map(UserPrincipal::from)
                    .orElseThrow(() -> new UserNotFoundException("User not found: " + email));

            if (jwtService.isTokenValid(token, userPrincipal)) {
                setAuthContext(userPrincipal, request);
            } else {
                String newToken = authService.refreshToken(refreshToken).getAccessToken();
                response.setHeader("Authorization", "Bearer " + newToken);
                setAuthContext(userPrincipal, request);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthContext(UserPrincipal userPrincipal, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userPrincipal,
                null,
                userPrincipal.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
