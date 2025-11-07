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

@Component                              // i am one filter that perform once for every request means will perform before every API
public class JwtAuthFilter extends OncePerRequestFilter {

    private  final MyUserDetailsService myUserDetailsService;

    public JwtAuthFilter(MyUserDetailsService userDetailsService) {
        this.myUserDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = JwtUtil.getUserNameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

//                take information from database (by username ) this information include id,username,password and role
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

                String role = JwtUtil.getRoleFromToken(token);
                List<SimpleGrantedAuthority> authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())
                );


// htis user is login this is her/his information and show the user permission and authorities
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//its like a box the spring security hold the current user login information
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }




}
