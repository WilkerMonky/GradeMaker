package com.datamonki.APIsCadastro.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@Column
	@NotNull
	private Integer carga;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne(mappedBy = "disciplina", cascade = CascadeType.ALL) // OBS
	private Oferta oferta;

	@ManyToMany
	@JoinTable(name = "professor_disciplina", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private Set<Professor> professores = new HashSet<>();

//	@JsonGetter("professores")
//	private Set<ProfessorDto> getProfessoresList(){
//		return professores.stream().map(pd-> new ProfessorDto(pd.getNome()).collect(Collectors.toSet()));
//	}
	
}
