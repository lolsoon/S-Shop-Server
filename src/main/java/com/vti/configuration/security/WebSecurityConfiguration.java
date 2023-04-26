package com.vti.configuration.security;

import com.vti.configuration.exception.AuthExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthExceptionHandler authExceptionHandler) throws Exception {
        return http
                .csrf().disable()
                .cors(withDefaults())
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(authExceptionHandler)
                        .accessDeniedHandler(authExceptionHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/api/v1/accounts/**")
                        .hasAnyAuthority("ADMIN")
                        .antMatchers(HttpMethod.DELETE)
                        .hasAnyAuthority("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/v1/products/**", "/api/v1/categories/**")
                        .hasAnyAuthority("ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/v1/products/**", "/api/v1/categories/**")
                        .hasAnyAuthority("ADMIN")
                        .antMatchers("/api/v1/auth/login/**", "/api/v1/auth/register/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
                HttpMethod.GET,
                "/swagger*/**",
                "/webjars/**",
                "/v2/api-docs"
        );
    }
}
