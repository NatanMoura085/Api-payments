package com.api_payments.api.controllers;

import com.api_payments.api.assembler.Assembler;
import com.api_payments.api.dto.UsuarioLojistaDTO;
import com.api_payments.api.dto.inputDTO.UsuarioLojistaInputDTO;
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
    private final Assembler assembler;
    @RequestMapping("/lojista")
    public List<UsuarioLojistaDTO> buscaTodosUsuarioLojista(){
        return assembler.toCollectionMapLojista(usuarioLojistaService.buscaTodosLojista());
    }
 @RequestMapping("/lojista/{id}")
 @GetMapping
 public ResponseEntity<UsuarioLojista> buscaPeloIdLojistaUsuario(@PathVariable Long id){
        return usuarioLojistaService.buscaPorIdLojista(id);
 }

 @PostMapping("/lojista")
 public UsuarioLojistaDTO cadastrar(@Valid @RequestBody UsuarioLojistaInputDTO usuarioLojistaInputDTO){
        UsuarioLojista usuarioLojista = assembler.toEntityLojista(usuarioLojistaInputDTO);
        return assembler.convertEntityToDTOLojista(usuarioLojistaService.cadastrarLojista(usuarioLojista));
 }

 @PutMapping("/lojista/{id}")
 public ResponseEntity<UsuarioLojista> autualizarLojista(@PathVariable Long id, @Valid @RequestBody UsuarioLojista usuarioLojista){
        return usuarioLojistaService.atualizar(id,usuarioLojista);
 }

 @DeleteMapping("/lojista/{id}")
 public void removendoLojista(@PathVariable Long id){
         usuarioLojistaService.remove(id);
 }
}
