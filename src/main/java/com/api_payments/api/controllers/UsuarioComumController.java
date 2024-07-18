package com.api_payments.api.controllers;

import com.api_payments.api.assembler.Assembler;
import com.api_payments.api.dto.UsuarioComumDTO;
import com.api_payments.api.dto.inputDTO.UsuarioComumInputDTO;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.service.UsuarioComumService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/api")
public class UsuarioComumController {
    private final UsuarioComumService usuarioComumService;
private final Assembler assembler;
    @GetMapping("/usuarios")
    public List<UsuarioComum> buscarTodos(){
        return usuarioComumService.buscar();
    }
    @GetMapping("/usuarios/{usuarioComumId}")
    public ResponseEntity<UsuarioComum> buscaUsuarioComumPeloId(@PathVariable Long usuarioComumId){
    return usuarioComumService.buscarPeloId(usuarioComumId);
}
@ResponseStatus(HttpStatus.OK)
@PostMapping("/usuarios")
public UsuarioComumDTO cadastraUsuarioComum(@Valid @RequestBody UsuarioComumInputDTO usuarioComumInputDTO){
        UsuarioComum usuarioComum =assembler.toEntity(usuarioComumInputDTO);
       return assembler.convertEntityToDTO(usuarioComumService.cadastra(usuarioComum));

}

@PutMapping("/usuarios/{id}")
public ResponseEntity<UsuarioComum> atualizarUsuarioComum(@Valid @RequestBody UsuarioComum usuarioComum, @PathVariable Long id){
        return usuarioComumService.atualizar(id,usuarioComum);
}

@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping("/usuarios/{id}")
public ResponseEntity<Void> deletandoUsuarioComum(@PathVariable Long id){
         usuarioComumService.removendo(id);
       return ResponseEntity.noContent().build();
}


}


