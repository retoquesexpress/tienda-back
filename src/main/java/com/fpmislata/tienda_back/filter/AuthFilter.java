/*
package com.fpmislata.tienda_back.config;


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

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String tokenString = authHeader.substring(7);
            Token token = new Token(tokenString, null);
            try {
                User user = authService.getUserFromToken(token);
                httpRequest.setAttribute("authenticatedUser", user);
                chain.doFilter(request, response);
            } catch (RuntimeException e) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");

            }
            }
        }
    }

*/
