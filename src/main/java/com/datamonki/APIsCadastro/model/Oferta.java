package com.datamonki.APIsCadastro.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OFERTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NotNull(message = "Nome não pode ser vazio")
	private Integer semestre;

	@ManyToOne
	@JoinColumn(name = "matriz_id")
	private Matriz matriz;

	@OneToOne // OBS
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

}
