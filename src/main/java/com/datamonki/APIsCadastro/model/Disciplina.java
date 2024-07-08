package com.datamonki.APIsCadastro.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.fasterxml.jackson.annotation.JsonGetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DISCIPLINA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@NotBlank
	private String nome;
	
	@OneToMany(mappedBy = "disciplina")
	private Set<ProfessorDisciplina> professores;

	@JsonGetter("professores")
	public Set<ProfessorDto> getProfessoresList(){
		return professores.stream().map(pd-> new ProfessorDto(pd.getProfessor().getId() ,pd.getProfessor().getNome()))
				.collect(Collectors.toSet());
	}
	
//	@JsonGetter("professores")
//	private Set<ProfessorDto> getProfessoresList(){
//		return professores.stream().map(pd-> new ProfessorDto(pd.getNome()).collect(Collectors.toSet()));
//	}
	
}
