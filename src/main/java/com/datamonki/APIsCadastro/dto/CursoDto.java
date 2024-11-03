package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Classe que representa o dto(data transfer object) de curso para a api
public record CursoDto(@NotBlank String nome, @NotNull Integer tipoCursoId) {

}