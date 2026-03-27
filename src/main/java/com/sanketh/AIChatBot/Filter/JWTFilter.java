package com.sanketh.AIChatBot.Filter;

import com.sanketh.AIChatBot.Security.UserServiceImpl;
import com.sanketh.AIChatBot.Utilis.JWTUtilizer;
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
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtilizer jwtUtilizer;
    private final UserServiceImpl userServiceImpl;


    public JWTFilter(JWTUtilizer jwtUtilizer, UserServiceImpl userServiceImpl) {
        this.jwtUtilizer = jwtUtilizer;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username=null;
         String token=null;
        String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token= authorizationHeader.substring(7);
            username = jwtUtilizer.extractUsername(token);
    }
    if (username != null) {
        UserDetails user=userServiceImpl.loadUserByUsername(username);
        if (user != null) {
          if (jwtUtilizer.validateToken(token, user.getUsername()))
          {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

          }
        }

    }
        filterChain.doFilter(request, response);
    }

}
