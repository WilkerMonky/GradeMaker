package com.datamonki.APIsCadastro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@Table(name = "dia_semana")
public class DiaSemana {
	@Id
	private Integer id;
	private String nome;
	
	public static DiaSemana retornar_dia_semana(@NotNull Integer dia_semana_id) {
		return null;
	}
}