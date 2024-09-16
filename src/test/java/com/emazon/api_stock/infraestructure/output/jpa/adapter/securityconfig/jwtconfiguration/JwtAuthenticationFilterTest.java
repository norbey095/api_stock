package com.emazon.api_stock.infraestructure.output.jpa.adapter.securityconfig.jwtconfiguration;

import static org.mockito.Mockito.*;
import static org.springframework.security.core.context.SecurityContextHolder.*;

import com.emazon.api_stock.infraestructure.securityconfig.UserDetailService;
import com.emazon.api_stock.infraestructure.securityconfig.jwtconfiguration.JwtAuthenticationFilter;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContext;
import java.lang.reflect.Method;

class JwtAuthenticationFilterTest {

    @Mock
    private UserDetailService userDetailService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal_NoAuthHeader() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader(InfraestructureConstants.AUTHORIZATION)).thenReturn(null);

        invokeDoFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        Assertions.assertNull(getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_InvalidAuthHeader() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader(InfraestructureConstants.AUTHORIZATION))
                .thenReturn(InfraestructureConstants.INVALID_HEADER);

        invokeDoFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        Assertions.assertNull(getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_ValidAuthHeader() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getHeader(InfraestructureConstants.AUTHORIZATION)).thenReturn(InfraestructureConstants.BEARER
                + InfraestructureConstants.ADMIN);
        when(userDetailService.loadUserByUsername(InfraestructureConstants.ADMIN)).thenReturn(mock(UserDetails.class));

        invokeDoFilterInternal(request, response, filterChain);

        SecurityContext securityContext = getContext();
        Assertions.assertNotNull(securityContext.getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    private void invokeDoFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain) throws Exception {
        Method method = JwtAuthenticationFilter.class.getDeclaredMethod(
                InfraestructureConstants.FILTER_INTERNAL, HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        method.setAccessible(true);
        method.invoke(jwtAuthenticationFilter, request, response, filterChain);
    }
}

