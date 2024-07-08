package com.datamonki.APIsCadastro.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professor_disciplina")
@Getter
@Setter
public class ProfessorDisciplina {
	
	
	
	
	@EmbeddedId
	private ProfessorDisciplinaId id;
	
	@ManyToOne
	@MapsId("professorId")
	@JoinColumn(name = "professor_id")
    @JsonBackReference
	private Professor professor;
	
	@ManyToOne
	@MapsId("disciplinaId")
    @JsonBackReference
	@JoinColumn(name="disciplina_id")
	private Disciplina disciplina;

    public ProfessorDisciplina() {}

    // Construtor com parâmetros
    public ProfessorDisciplina(ProfessorDisciplinaId id) {
        this.id = id;
    }

	
}
