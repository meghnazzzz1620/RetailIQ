package com.retailiq.security;

import com.retailiq.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("========== JWT FILTER ==========");
        System.out.println("Request: " + request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header = " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            System.out.println("No Bearer token found.");

            filterChain.doFilter(request, response);

            return;
        }

        try {

            String jwt = authHeader.substring(7);

            System.out.println("JWT = " + jwt);

            String email = jwtService.extractUsername(jwt);

            System.out.println("Email = " + email);

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            System.out.println("Authorities = " + userDetails.getAuthorities());

            if (jwtService.isTokenValid(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);

                System.out.println("Authentication SUCCESS");

            } else {

                System.out.println("Token INVALID");

            }

        } catch (Exception e) {

            System.out.println("JWT Exception");
            e.printStackTrace();

        }

        filterChain.doFilter(request, response);

    }

}