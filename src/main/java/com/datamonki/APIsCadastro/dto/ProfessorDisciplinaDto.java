package com.datamonki.APIsCadastro.dto;

//Classe que representa o dto(data transfer object) de professor disciplina para a api
public record ProfessorDisciplinaDto(ProfessorDto professor, DisciplinaDto disciplina) {

}