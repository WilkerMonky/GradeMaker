package com.datamonki.APIsCadastro.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProfessorDisciplinaId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @NotNull(message="professorId obrigatório")
	private Integer professorId;
    @NotNull(message = "disciplinaId obrigatório")
    private Integer disciplinaId;

    public ProfessorDisciplinaId() {}

    public ProfessorDisciplinaId(Integer professorId, Integer disciplinaId) {
        this.professorId = professorId;
        this.disciplinaId = disciplinaId;
    }

    // getters, setters, equals, and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorDisciplinaId that = (ProfessorDisciplinaId) o;
        return Objects.equals(professorId, that.professorId) &&
               Objects.equals(disciplinaId, that.disciplinaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, disciplinaId);
    }
}

