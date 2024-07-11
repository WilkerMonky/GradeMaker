package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfessorDto(Integer id, @NotBlank String nome) {

}
