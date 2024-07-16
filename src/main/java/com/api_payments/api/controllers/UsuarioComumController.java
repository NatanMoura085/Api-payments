package com.api_payments.api.controllers;

import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.service.UsuarioComumService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/api")
public class UsuarioComumController {
    private final UsuarioComumService usuarioComumService;
    @GetMapping("/usuarios")
    public List<UsuarioComum> buscarTodos(){
        return usuarioComumService.buscar();
    }
    @GetMapping("/usuarios/{usuarioComumId}")
    public ResponseEntity<UsuarioComum> buscaUsuarioComumPeloId(@PathVariable Long usuarioComumId){
    return usuarioComumService.buscarPeloId(usuarioComumId);
}
}


