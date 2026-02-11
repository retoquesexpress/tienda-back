package com.fpmislata.tienda_back.filter;

import com.fpmislata.tienda_back.domain.model.Token;
import com.fpmislata.tienda_back.domain.model.User;
import com.fpmislata.tienda_back.domain.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private final AuthService authService;

    public AuthFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String path = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        if (path.startsWith("/api/auth")) {
            chain.doFilter(request, response);
            return;
        }

        boolean isPublicGet = "GET".equalsIgnoreCase(method)
                && (path.startsWith("/api/services") || path.startsWith("/api/categories"));

        if (isPublicGet) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token required");
            return;
        }

        String tokenString = authHeader.substring(7);
        Token token = new Token(tokenString, null);

        try {
            User user = authService.getUserFromToken(token);

            boolean isModifyingRestricted = !"GET".equalsIgnoreCase(method)
                    && (path.startsWith("/api/services") || path.startsWith("/api/categories"));

            if (isModifyingRestricted && !"admin".equalsIgnoreCase(user.getRole())) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Admin role required for this action");
                return;
            }

            httpRequest.setAttribute("authenticatedUser", user);
            chain.doFilter(request, response);

        } catch (RuntimeException e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        }
    }
}
