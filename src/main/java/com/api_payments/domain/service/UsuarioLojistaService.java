package com.api_payments.domain.service;

import com.api_payments.domain.exceptionhandler.LojistaException;
import com.api_payments.domain.model.UsuarioLojista;
import com.api_payments.domain.repository.UsuarioLojistaRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioLojistaService {
    private final UsuarioLojistaRepository usuarioLojistaRepository;

    @Transactional
    public List<UsuarioLojista> buscaTodosLojista(){
        return usuarioLojistaRepository.findAll();
    }

  @Transactional
    public ResponseEntity<UsuarioLojista> buscaPorIdLojista(Long id){
        return usuarioLojistaRepository.findById(id).map(ResponseEntity::ok).orElseThrow(()->  new LojistaException("id do lojista nao existe"));
    }

    @Transactional
    public UsuarioLojista cadastrarLojista(@Valid  UsuarioLojista usuarioLojista){
        Optional cpfEmUso = usuarioLojistaRepository.findBycpf(usuarioLojista.getCpf());

        if(cpfEmUso.isPresent()){
            throw new LojistaException("cpf do lojista esta em uso");
        }
        return usuarioLojistaRepository.save(usuarioLojista);
    }

    @Transactional
    public ResponseEntity<UsuarioLojista> atualizar(Long id,UsuarioLojista usuarioLojista){
      if (!usuarioLojistaRepository.existsById(id)){
       return ResponseEntity.notFound().build();
      }
      usuarioLojista.setId(id);
      UsuarioLojista usuarioLojistaa = usuarioLojistaRepository.save(usuarioLojista);

    return ResponseEntity.ok(usuarioLojistaa);
    }
}
