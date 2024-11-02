package com.datamonki.APIsCadastro.exception;

public class IdNaoEncontradoException extends RuntimeException {


	public IdNaoEncontradoException() {
		super("Não foi possivel encontrar com o Id informado, verifique e tente novamente");
	}
	
    public IdNaoEncontradoException(String message) {
        super(message);
    }
	
	private static final long serialVersionUID = 1L;
}