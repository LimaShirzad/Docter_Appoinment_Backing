//package com.doctoreappointmentProject.doctoreappointmentProject.exception;
//
//
//import com.doctoreappointmentProject.doctoreappointmentProject.service.JwtService;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private  final JwtService jwtService;
//
//    public JwtAuthFilter(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String authHeader=request.getHeader("Authorization");
//        if(authHeader==null || !authHeader.startsWith("Bearer")){
//
//            filterChain.doFilter(request,response);
//
//            return;
//
//        }
//
//        String token=authHeader.substring(7);
//        try {
//
//
//            Claims claim = jwtService.parseToken(token);
//            String username = claim.getSubject();
//            String role = claim.get("role", String.class);
//
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(() -> "ROLE_" + role));
//
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }catch (Exception e){
//            System.out.println("invalid" + e.getMessage());
//        }
//        filterChain.doFilter(request,response);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//}
