package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;

//Classe que representa o dto(data transfer object) de tipo de curso para a api
public record TipoCursoDto(@NotBlank String nome) {

}