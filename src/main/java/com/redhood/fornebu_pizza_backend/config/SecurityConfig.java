package com.redhood.fornebu_pizza_backend.config;

import com.google.cloud.firestore.Firestore;
import com.redhood.fornebu_pizza_backend.filters.FirebaseAuthFilter;
import com.redhood.fornebu_pizza_backend.service.FirebaseAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final FirebaseAuthService firebaseAuthService;
    private final Firestore firestore;

    public SecurityConfig(FirebaseAuthService firebaseAuthService, Firestore firestore) {
        this.firebaseAuthService = firebaseAuthService;
        this.firestore = firestore;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/orders/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/orders/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/orders/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new FirebaseAuthFilter(firebaseAuthService,firestore), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
