package com.nagina_international.OMS_V1.security.config;


import com.nagina_international.OMS_V1.security.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public final JwtFilter jwtFilter;
    public final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtFilter jwtFilter, AuthenticationProvider authenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) //this line has made me to feel so trouble
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()

                                //users-permissions
                                .requestMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/user/**").hasAnyRole("ADMIN", "USER")

                                //admin-permissions
                                .requestMatchers(HttpMethod.GET,"/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/admin/**").hasRole("ADMIN")

                                //dispatcher-permissions
                                .requestMatchers(HttpMethod.GET, "/api/v1/dispatcher/**").hasAnyRole("DISPATCHER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/dispatcher/**").hasAnyRole("DISPATCHER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/dispatcher/**").hasAnyRole("DISPATCHER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/dispatcher/**").hasAnyRole("DISPATCHER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/dispatcher/**").hasAnyRole("DISPATCHER", "ADMIN")

                                //packager-permissions
                                .requestMatchers(HttpMethod.GET, "/api/v1/packager/**").hasAnyRole("PACKAGER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/packager/**").hasAnyRole("PACKAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/packager/**").hasAnyRole("PACKAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/packager/**").hasAnyRole("PACKAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/packager/**").hasAnyRole("PACKAGER", "ADMIN")

                                //storemanager-permissions
                                .requestMatchers(HttpMethod.GET, "/api/v1/storemanager/**").hasAnyRole("STOREMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/storemanager/**").hasAnyRole("STOREMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/storemanager/**").hasAnyRole("STOREMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/storemanager/**").hasAnyRole("STOREMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/storemanager/**").hasAnyRole("STOREMANAGER", "ADMIN")

                                //stockmanager-permissions
                                .requestMatchers(HttpMethod.GET, "/api/v1/stockmanager/**").hasAnyRole("STOCKMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/stockmanager/**").hasAnyRole("STOCKMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/stockmanager/**").hasAnyRole("STOCKMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/stockmanager/**").hasAnyRole("STOCKMANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/stockmanager/**").hasAnyRole("STOCKMANAGER", "ADMIN")

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }
}
