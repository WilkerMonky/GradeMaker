package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotBlank;

//Classe que representa o dto(data transfer object) de disciplina para a api
public record DisciplinaDto(@NotBlank String nome) { 
    
}