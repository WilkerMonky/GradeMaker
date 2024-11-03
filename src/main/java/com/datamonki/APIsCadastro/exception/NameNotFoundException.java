package com.datamonki.APIsCadastro.exception;

//Classe que trata as excecoes de nome nao encontrado na api
public class NameNotFoundException extends RuntimeException {
    
    public NameNotFoundException(String message) {
        super(message);
    }
    
    public NameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private static final long serialVersionUID = 1L;
}