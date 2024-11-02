package com.datamonki.APIsCadastro.exception;

import java.util.List;

public class ValidarException extends RuntimeException {

	private static final long serialVersionUID = 1L; 

	private List<String> erros; 

	public ValidarException(String erro) {
		super(erro); 
		this.erros.add(erro); 
	}

	public List<String> getErros() {
		return erros;
	}
}