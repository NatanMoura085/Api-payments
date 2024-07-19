package com.api_payments.api.controllers;

import com.api_payments.domain.model.UsuarioLojista;
import com.api_payments.domain.service.UsuarioLojistaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 @RequestMapping("/lojista/{id}")
 @GetMapping
 public ResponseEntity<UsuarioLojista> buscaPeloIdLojistaUsuario(@PathVariable Long id){
        return usuarioLojistaService.buscaPorIdLojista(id);
 }

 @PostMapping("/lojista")
 public UsuarioLojista cadastrar(@Valid @RequestBody UsuarioLojista usuarioLojista){
        return usuarioLojistaService.cadastrarLojista(usuarioLojista);
 }
}
