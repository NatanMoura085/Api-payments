package com.api_payments.domain.factory.abstratcfactory;

import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.model.UsuarioComum;

public class UsuarioComumFactory implements UsuarioAbstractFactory{
    @Override
    public UsuarioComum createUsuarioComum() {
        return new UsuarioComum();
    }

    @Override
    public Transaction createTransacations() {
        return new Transaction();
    }
}
