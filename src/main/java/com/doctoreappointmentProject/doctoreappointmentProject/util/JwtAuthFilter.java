package com.doctoreappointmentProject.doctoreappointmentProject.util;

import com.doctoreappointmentProject.doctoreappointmentProject.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private  final MyUserDetailsService userDetailsService;

    public JwtAuthFilter(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = JwtUtil.getUserNameFromToken(token);

            // ✅ Only if username exists and no authentication yet
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Load user details from DB
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // ✅ Here is where your lines go:
                String role = JwtUtil.getRoleFromToken(token);
                List<SimpleGrantedAuthority> authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())
                );
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set authentication in context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }




}
