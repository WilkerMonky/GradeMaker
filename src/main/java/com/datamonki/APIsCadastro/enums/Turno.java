package com.datamonki.APIsCadastro.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public enum Turno {

	MATUTINO(1, "Matutino", "8:30:00", "11:10:00"), NOTURNO(2, "Noturno", "18:00:00", "21:00:00");
	
	@Id
	private Integer id;
	private String nome;
	private String horaInicio;
	private String horaFim;

	private Turno(Integer id, String nome, String horaInicio, String horaFim) {
		this.id = id;
		this.nome = nome;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	public static Turno retornar_turno(Integer id) {
		if (id == 1)
			return MATUTINO;
		else if (id == 2)
			return NOTURNO;
		else
			return null;
	}

}
