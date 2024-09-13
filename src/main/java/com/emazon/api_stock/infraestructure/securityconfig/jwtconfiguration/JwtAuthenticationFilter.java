package com.emazon.api_stock.infraestructure.securityconfig.jwtconfiguration;

import com.emazon.api_stock.infraestructure.securityconfig.UserDetailService;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(InfraestructureConstants.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(InfraestructureConstants.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(InfraestructureConstants.VALUE_7);
        UserDetails user = userDetailService.loadUserByUsername(jwt);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, jwt,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}


