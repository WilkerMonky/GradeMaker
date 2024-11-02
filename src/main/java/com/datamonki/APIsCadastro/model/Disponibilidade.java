package com.datamonki.APIsCadastro.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disponibilidade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Disponibilidade  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NotNull
	private Integer semestre;

	@Column
	@NotNull
	private Integer ano;
	
	@ManyToOne
	@JoinColumn(name= "professor_id")
	@NotNull(message = "Professor_id não pode ser nulo")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "dia_semana_id")
	private DiaSemana diaSemana;
	
	@ManyToOne
	@JoinColumn(name="turno_id")
	private Turno turno;
}