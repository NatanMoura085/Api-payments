package com.api_payments.domain.service;

import com.api_payments.domain.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticaçãoService implements UserDetailsService {
    @Autowired
    private  LoginRepository loginRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return loginRepository.findBylogin(login);
    }
}
