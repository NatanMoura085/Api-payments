package com.api_payments.api.assembler;

import com.api_payments.api.dto.UsuarioComumDTO;
import com.api_payments.api.dto.inputDTO.UsuarioComumInputDTO;
import com.api_payments.domain.model.UsuarioComum;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Assembler {
    private ModelMapper modelMapper;

    public UsuarioComum toEntity(@Valid UsuarioComumInputDTO usuarioComumDTO) {
        return modelMapper.map(usuarioComumDTO, UsuarioComum.class);
    }

    public UsuarioComumDTO convertEntityToDTO(UsuarioComum usuarioComum){
        return modelMapper.map(usuarioComum,UsuarioComumDTO.class);
    }

//    public List<UsuarioComum> toCollectionMap(List<UsuarioComum> usuarioComums){
//        return modelMapper;
//   }

}
