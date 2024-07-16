package com.api_payments.domain.service;

import com.api_payments.domain.exceptionhandler.UsuarioException;
import com.api_payments.domain.model.UsuarioComum;
import com.api_payments.domain.repository.UsuarioComumRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioComumService {
    private UsuarioComumRepository usuarioComumRepository;
    @Transactional
    public List<UsuarioComum> buscar(){
        return usuarioComumRepository.findAll();
    }
    @Transactional
    public ResponseEntity<UsuarioComum> buscarPeloId(Long id){
        return usuarioComumRepository.findById(id).map(ResponseEntity::ok).orElseThrow(()-> new UsuarioException("usuario com esse id nao existir"));
    }
    @Transactional
    public UsuarioComum cadastra(UsuarioComum usuarioComum){

        return usuarioComumRepository.save(usuarioComum);
    }
}
