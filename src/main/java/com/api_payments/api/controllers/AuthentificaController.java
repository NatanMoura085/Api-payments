package com.api_payments.api.controllers;

import com.api_payments.api.dto.AuthDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
@AllArgsConstructor
public class AuthentificaController {
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO) {
        var usuarioAuthentificaçaoToken = new UsernamePasswordAuthenticationToken(authDTO.getLogin(), authDTO.getSenha());
        authenticationManager.authenticate(usuarioAuthentificaçaoToken);
        return "token...";
    }
}
