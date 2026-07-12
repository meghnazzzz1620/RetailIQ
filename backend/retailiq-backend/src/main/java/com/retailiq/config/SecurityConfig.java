package com.retailiq.config;

import com.retailiq.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()

                        // Dashboard
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/dashboard/**")
                        .hasAnyRole("ADMIN","MANAGER","EMPLOYEE")

                        // Products
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/products/**")
                        .hasAnyRole("ADMIN","MANAGER","EMPLOYEE")

                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/products/**")
                        .hasAnyRole("ADMIN","MANAGER")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/v1/products/**")
                        .hasAnyRole("ADMIN","MANAGER")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/v1/products/**")
                        .hasRole("ADMIN")

                        // Employees
                        .requestMatchers("/api/v1/employees/**")
                        .hasRole("ADMIN")


                        // Orders
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/orders/**")
                        .hasAnyRole("ADMIN","MANAGER","EMPLOYEE")

                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/orders/**")
                        .hasAnyRole("ADMIN","MANAGER")

                        // Suppliers
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/suppliers/**")
                        .hasAnyRole("ADMIN","MANAGER")

                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/suppliers/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/v1/suppliers/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/v1/suppliers/**")
                        .hasRole("ADMIN")

                        // Reports
                        .requestMatchers("/api/v1/reports/**")
                        .hasAnyRole("ADMIN","MANAGER")

                        .requestMatchers("/api/v1/ai/**")
                        .permitAll()
                        // Everything else
                        .anyRequest()
                        .authenticated()

                )

                .addFilterBefore(

                        jwtAuthenticationFilter,

                        UsernamePasswordAuthenticationFilter.class)

                ;

        return http.build();

    }

}