package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatrizDto(@NotBlank String nome,
						@NotNull Integer cursoId) {

}
