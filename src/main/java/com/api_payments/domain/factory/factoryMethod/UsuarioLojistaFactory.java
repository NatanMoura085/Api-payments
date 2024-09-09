package com.api_payments.domain.factory.factoryMethod;

import com.api_payments.domain.model.UsuarioLojista;

import java.math.BigDecimal;

public class UsuarioLojistaFactory extends UsuarioFactory<UsuarioLojista> {
    @Override
    public UsuarioLojista createUsuario() {
        UsuarioLojista usuarioLojista = new UsuarioLojista();
        usuarioLojista.setSaldoConta(BigDecimal.valueOf(100.00));
        return usuarioLojista;
    }
}
