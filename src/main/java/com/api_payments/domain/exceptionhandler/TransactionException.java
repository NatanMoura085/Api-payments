package com.api_payments.domain.exceptionhandler;

public class TransactionException extends RuntimeException{
    public TransactionException(String message){
        super(message);
    }
}
