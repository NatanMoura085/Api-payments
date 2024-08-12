package com.api_payments.domain.service;

import com.api_payments.api.dto.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticaçaoService extends UserDetailsService {
    public String obterToken(AuthDTO authDTO);
}
