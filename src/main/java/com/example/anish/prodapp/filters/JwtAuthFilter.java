package com.example.anish.prodapp.filters;

import com.example.anish.prodapp.entity.User;
import com.example.anish.prodapp.service.JwtService;
import com.example.anish.prodapp.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract token
        String token = requestTokenHeader.substring(7);

        Long userId = jwtService.getUserIdFromToken(token);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.getUserById(userId);

            // You can provide user.getAuthorities() instead of null for role-based auth
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, null);

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
