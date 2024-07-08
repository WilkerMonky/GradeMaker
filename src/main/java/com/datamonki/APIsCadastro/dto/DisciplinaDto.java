package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaDto(@NotNull Integer id, @NotBlank String nome) {



}
