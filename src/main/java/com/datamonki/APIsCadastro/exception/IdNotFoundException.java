package com.datamonki.APIsCadastro.exception;

//Classe que trata as excecoes de id nao encontrado na api
public class IdNotFoundException extends RuntimeException {
	
	public IdNotFoundException(String message) { 
		super(message);
	}

	public IdNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	private static final long serialVersionUID = 1L;
}