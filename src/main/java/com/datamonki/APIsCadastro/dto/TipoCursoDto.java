package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoCursoDto(@NotBlank String nome) {

}
