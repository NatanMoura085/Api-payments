package com.api_payments.api.controllers;

import com.api_payments.domain.model.UsuarioLojista;
import com.api_payments.domain.service.UsuarioLojistaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/api")
public class UsuarioLojistaController {
    private final UsuarioLojistaService usuarioLojistaService;
    @RequestMapping("/lojista")
    public List<UsuarioLojista> buscaTodosUsuarioLojista(){
        return usuarioLojistaService.buscaTodosLojista();
    }


}
