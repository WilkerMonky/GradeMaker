package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;

//Classe que representa o dto(data transfer object) de professor para a api
public record ProfessorDto(@NotBlank String nome) {

}