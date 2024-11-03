package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotNull;

//Classe que representa o dto(data transfer object) de disponibilidade para a api   
public record DisponibilidadeDto(@NotNull Integer professorId,
                @NotNull Integer diaSemanaId, @NotNull Integer turnoId, @NotNull Integer semestre, @NotNull Integer ano) {

}