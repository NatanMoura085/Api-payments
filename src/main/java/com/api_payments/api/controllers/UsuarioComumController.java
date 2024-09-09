package com.api_payments.api.controllers;

import com.api_payments.api.assembler.Assembler;
import com.api_payments.api.dto.UsuarioComumDTO;
import com.api_payments.api.dto.inputDTO.UsuarioComumInputDTO;
import com.api_payments.domain.factory.factoryMethod.UsuarioComumFactory;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.service.UsuarioComumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public List<UsuarioComumDTO> buscarTodos() {
        return assembler.toCollectionMap(usuarioComumService.buscar());
    }


    @GetMapping("/usuarios/{usuarioComumId}")
    public ResponseEntity<UsuarioComum> buscaUsuarioComumPeloId(@PathVariable Long usuarioComumId) {
        return usuarioComumService.buscarPeloId(usuarioComumId);
    }

    @Operation(summary = "Busca pelo todos user", description = "retorna usuarios lista e seus dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "UsuarioComum Não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/usuarios")
    public UsuarioComumDTO cadastraUsuarioComum(@Valid @RequestBody UsuarioComumInputDTO usuarioComumInputDTO) {
       // UsuarioComumFactory factory  = new UsuarioComumFactory();
       // UsuarioComum usuario = factory.createUsuarioComum();

        UsuarioComum usuarioComum = assembler.toEntity(usuarioComumInputDTO);
        return assembler.convertEntityToDTO(usuarioComumService.cadastra(usuarioComum));

    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioComumDTO> atualizarUsuarioComum(@Valid @RequestBody UsuarioComumInputDTO usuarioComumInputDTO, @PathVariable Long id) {
        return usuarioComumService.atualizar(id, assembler.toEntity(usuarioComumInputDTO));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletandoUsuarioComum(@PathVariable Long id) {
        usuarioComumService.removendo(id);
        return ResponseEntity.noContent().build();
    }


}


