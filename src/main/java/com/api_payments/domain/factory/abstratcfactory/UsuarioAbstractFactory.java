package com.api_payments.domain.factory.abstratcfactory;

import com.api_payments.domain.model.Transaction;
import com.api_payments.domain.model.UsuarioComum;

public interface UsuarioAbstractFactory {
    UsuarioComum createUsuarioComum();
    Transaction createTransacations();
}
