package com.api_payments.configuration;

import com.api_payments.api.dto.AuthDTO;
import com.api_payments.domain.model.Login;
import com.api_payments.domain.model.UsuarioComum;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Login, AuthDTO>() {
            @Override
            protected void configure() {
                map().setLogin(source.getLogin());
                map().setSenha(source.getSenha());
            }
        });


        return modelMapper;
    }

}
