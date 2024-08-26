package com.nagina_international.OMS_V1.security.jwt;

import com.nagina_international.OMS_V1.repository.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserRepository userRepository;


    @Autowired
    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService, HandlerExceptionResolver handlerExceptionResolver, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userName = jwtService.extractUserName(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            logger.debug("Request to '{}', Authentication: {}", request.getRequestURI(), authentication);

            if (userName != null && authentication == null) {
                UserDetails userDetails = userRepository.
                        findByEmail(userName)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not found with email: " + userName));

                if (jwtService.isTokenValid(jwt, userDetails)) {

                    List<String> roles = jwtService.extractRoles(jwt);

                    logger.debug("Extracted username from JWT: {}", userName);
                    logger.debug("Extracted roles from JWT: {}", roles);

                    List<SimpleGrantedAuthority> authorities = roles
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role))
                            .toList();

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    logger.debug("Set authentication in SecurityContext: {}", SecurityContextHolder.getContext().getAuthentication());
                }
            } else {
                logger.warn("Token is not validated for the user: {}", userName);
            }

            filterChain.doFilter(request, response);


        } catch (Exception e) {
            logger.error("The issue exist in the method doFilterInternal in the class JwtFilter", e);
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

    }
}
