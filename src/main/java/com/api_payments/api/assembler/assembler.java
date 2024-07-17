package com.api_payments.api.assembler;

import com.api_payments.api.dto.UsuarioComumDTO;
import com.api_payments.domain.model.UsuarioComum;
import org.modelmapper.ModelMapper;

import java.util.List;

public class assembler {
    private ModelMapper modelMapper;

    public UsuarioComum toEntity(UsuarioComumDTO usuarioComumDTO) {
        return modelMapper.map(usuarioComumDTO, UsuarioComum.class);
    }

//    public List<UsuarioComum> toCollectionMap(List<UsuarioComum> usuarioComums){
//        return modelMapper.map(usuarioComums.)
//    }
}
