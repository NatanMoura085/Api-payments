package com.api_payments.domain.exceptionhandler;

public class UsuarioException extends RuntimeException{
    public UsuarioException(String message){
        super(message);
    }
}
