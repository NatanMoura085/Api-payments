package com.api_payments.api.assembler;

import com.api_payments.api.dto.UsuarioComumDTO;
import com.api_payments.domain.model.UsuarioComum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Assembler {
    private ModelMapper modelMapper;

    public UsuarioComum toEntity(UsuarioComumDTO usuarioComumDTO) {
        return modelMapper.map(usuarioComumDTO, UsuarioComum.class);
    }

    public UsuarioComumDTO toModelMap(UsuarioComum usuarioComum){
        return modelMapper.map(usuarioComum,UsuarioComumDTO.class);
    }

//    public List<UsuarioComum> toCollectionMap(List<UsuarioComum> usuarioComums){
//        return modelMapper;
//   }

}
