package com.datamonki.APIsCadastro.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import com.datamonki.APIsCadastro.dto.DisciplinaDto;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "professor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "professor")
	private Set<ProfessorDisciplina> disciplinas;
	
	
	@JsonGetter("disciplinas")
	public Set<DisciplinaDto> getDisciplinasList(){
		return disciplinas.stream().map(pd -> new DisciplinaDto(pd.getDisciplina().getId(), pd.getDisciplina().getNome())).collect(Collectors.toSet());
	}
}

	



