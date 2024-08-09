package com.api_payments.domain.service;

import com.api_payments.domain.exceptionhandler.UsuarioException;
import com.api_payments.domain.repository.LoginRepository;
import com.api_payments.domain.repository.UsuarioComumRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AutenticaçãoService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AutenticaçãoService.class);

    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.error(login,loginRepository.findBylogin(login));
        return  loginRepository.findBylogin(login).orElseThrow(()-> new UsuarioException("Nao encontrou natan o login"));
    }
}
