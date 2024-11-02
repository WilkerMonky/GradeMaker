package com.datamonki.APIsCadastro.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disciplina")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NotBlank
	private String nome;
	
	@OneToMany(mappedBy = "disciplina")
	@JsonIgnore
	private Set<ProfessorDisciplina> professores;

	@JsonGetter("professores")
	public Set<ProfessorDto> getProfessoresList() {
		return professores.stream().map(pd -> new ProfessorDto(pd.getProfessor().getNome())) 
				.collect(Collectors.toSet());
	}
}