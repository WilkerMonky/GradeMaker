package com.datamonki.APIsCadastro.model;

import java.io.Serializable;

import com.datamonki.APIsCadastro.enums.DiaSemana;
import com.datamonki.APIsCadastro.enums.Turno;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DISPONIBILIDADE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disponibilidade  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
