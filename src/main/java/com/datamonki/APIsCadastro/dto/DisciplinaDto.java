package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;

public record DisciplinaDto(@NotBlank String nome) { 
    
}
