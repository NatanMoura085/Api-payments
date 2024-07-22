package com.api_payments.domain.exceptionhandler;

public class EntidadeNaoEncontrada extends RuntimeException{
    public EntidadeNaoEncontrada(String message){
        super(message);
    }
}
