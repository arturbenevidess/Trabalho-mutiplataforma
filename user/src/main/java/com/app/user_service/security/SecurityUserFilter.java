package com.app.user_service.security;

import java.io.IOException;
import java.util.List;

import com.app.user_service.providers.JWTUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityUserFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUserProvider jwtProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // Valida apenas se o token estiver presente
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            var jwtToken = this.jwtProvider.validateToken(token);

            if (jwtToken == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou expirado.");
                return;
            }

            String userId = jwtToken.getSubject();
            String requestUserId = request.getParameter("userId");

            // Valida se o userId no token corresponde ao userId da requisição
            if (requestUserId != null && !requestUserId.equals(userId)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Usuário não autorizado para essa ação.");
                return;
            }

            request.setAttribute("user_id", userId);

            List<Object> roles = jwtToken.getClaim("roles").asList(Object.class);

            var grants = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                    .toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userId, null, grants);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
