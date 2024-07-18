package com.api_payments.api.assembler;

import com.api_payments.api.dto.UsuarioComumDTO;
import com.api_payments.api.dto.inputDTO.UsuarioComumInputDTO;
import com.api_payments.domain.model.UsuarioComum;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<UsuarioComumDTO> toCollectionMap(List<UsuarioComum> usuarioComums){
       List<UsuarioComumDTO> dtos = usuarioComums.stream().map(usuarioComum -> modelMapper.map(usuarioComum,UsuarioComumDTO.class)).collect(Collectors.toList());
       return dtos;
   }

}
