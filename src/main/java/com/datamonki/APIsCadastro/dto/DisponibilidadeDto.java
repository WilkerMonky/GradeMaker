package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotNull;

public record DisponibilidadeDto(@NotNull Integer professorId, @NotNull Integer diaSemanaId, @NotNull Integer turnoId, @NotNull Integer semestre, @NotNull Integer ano) {

}
