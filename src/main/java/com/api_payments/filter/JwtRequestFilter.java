//package com.api_payments.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//    private final HandlerExceptionResolver handlerExceptionResolver;
//    private final UserDetailsService userDetailsService;
//
//    public JwtRequestFilter(UserDetailsService userDetailsService ,HandlerExceptionResolver handlerExceptionResolver) {
//        this.handlerExceptionResolver = handlerExceptionResolver;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwt = null;
//        if (authorizationHeader !=null && authorizationHeader.startsWith("Bearer")){
//            filterChain.doFilter(request, response);
//          jwt = authorizationHeader.substring(7);
//        }
//    }
//}
