package com.api_payments.api.controllers;

import com.api_payments.api.dto.AuthDTO;
import com.api_payments.domain.model.Login;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api")
@AllArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class AuthentificaController {
    private AuthenticationManager authenticationManager;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthentificaController.class);

    @PostMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO) {
        try {
            UsernamePasswordAuthenticationToken usuarioAuthentificaçaoToken = new UsernamePasswordAuthenticationToken(authDTO.getLogin(), authDTO.getSenha());
            authenticationManager.authenticate(usuarioAuthentificaçaoToken);
        } catch (Exception e) {
            logger.error("authentificaçao falhou em NATAN", authDTO.getLogin(), authDTO.getSenha().concat("{noop}"));
            throw new RuntimeException(e.getMessage());
        }
        return "token...";
    }

    @PostMapping("/registra")
    public Login registra(@RequestBody Login login) {
        login.setSenha(passwordEncoder.encode(login.getSenha()));
        return loginRepository.save(login);
    }
}
