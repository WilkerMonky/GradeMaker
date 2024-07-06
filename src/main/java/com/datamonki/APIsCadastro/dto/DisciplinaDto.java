package com.datamonki.APIsCadastro.dto;

import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaDto(@NotBlank String nome, @NonNull Integer carga, @NotNull Set<Integer> professor_id) {



}
