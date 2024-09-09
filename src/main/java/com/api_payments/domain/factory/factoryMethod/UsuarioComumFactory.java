package com.api_payments.domain.factory.factoryMethod;

import com.api_payments.domain.model.UsuarioComum;

public class UsuarioComumFactory extends UsuarioFactory<UsuarioComum> {

    @Override
    public UsuarioComum createUsuario() {
        return  new UsuarioComum();
    }
}
