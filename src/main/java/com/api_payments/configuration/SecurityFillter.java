package com.api_payments.configuration;

import com.api_payments.domain.model.Login;
import com.api_payments.domain.repository.LoginRepository;
import com.api_payments.domain.service.AutenticaçaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFillter extends OncePerRequestFilter {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AutenticaçaoService autenticaçaoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extrairToken(request);
        if (token != null) {
            String validarToken = autenticaçaoService.validarTokenJwt(token);
            Login login = loginRepository.findByLogin2(validarToken);
            var authenticationToken = new UsernamePasswordAuthenticationToken(validarToken,null,login.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request, response);
    }

    public String extrairToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");

        if (auth == null) {
            return null;
        }

        if (!auth.split(" ")[0].equals("Bearer")) {
            return null;

        }

        return auth.split(" ")[1];

    }
}
