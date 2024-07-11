package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDto(@NotBlank String nome, @NotNull Integer tipoCursoId) {

}
