package com.datamonki.APIsCadastro.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public enum DiaSemana {

	SEG(1, "Segunda"), TER(2, "Terça"), QUA(3, "Quarta"), QUI(4, "Quinta"), SEX(5, "Sexta");

	@Id
	private Integer id;
	private String nomeDiaSemana;

	private DiaSemana(Integer id, String nomeDiaSemana) {
		this.id = id;
		this.nomeDiaSemana = nomeDiaSemana;
	}

	public static DiaSemana retornar_dia_semana(Integer id) {
		if(id == 1)
			return SEG;
		else if(id == 2)
			return TER;
		else if(id == 3)
			return QUA;
		else if(id == 4)
			return QUI;
		else if(id == 5)
			return SEX;
		else
			return null;

	}
}
