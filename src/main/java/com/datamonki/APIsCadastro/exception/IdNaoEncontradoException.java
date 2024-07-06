package com.datamonki.APIsCadastro.exception;

public class IdNaoEncontradoException extends RuntimeException {


	public IdNaoEncontradoException() {
		super("Não foi possivel encontrar com o Id, verifique e tente novamente");
	}
	private static final long serialVersionUID = 1L;

}
