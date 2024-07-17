package com.api_payments.configuration;

import com.api_payments.api.dto.UsuarioComumDTO;
import com.api_payments.domain.model.UsuarioComum;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
