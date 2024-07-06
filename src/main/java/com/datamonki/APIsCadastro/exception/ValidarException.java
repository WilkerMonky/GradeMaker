package com.datamonki.APIsCadastro.exception;

import java.util.LinkedList;
import java.util.List;

public class ValidarException extends RuntimeException {

	private List<String> erros;

    public ValidarException(List<String> erros) {
        this.erros = erros;
    }

    public ValidarException(String erro) {
        this.erros = new LinkedList<>();
        this.erros.add(erro);
    }

	public List<String> getErros() {
		return erros;
	}

	private static final long serialVersionUID = 1L;
}
