package com.api_payments.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
    public class AuthDTO {
    private Long id;
    @NotBlank(message = "Login não pode ser vazio")
    @Column(name = "loguin")
    private String login;
    @NotBlank(message = "senha não pode ser vazio")
    private String senha;

}
