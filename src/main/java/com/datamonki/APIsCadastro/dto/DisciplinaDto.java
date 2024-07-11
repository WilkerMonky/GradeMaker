package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;

public record DisciplinaDto(Integer id, @NotBlank String nome) {

}
