package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotNull;

public record DisponibilidadeDto(@NotNull Integer professor_id, @NotNull Integer dia_semana_id, @NotNull Integer turno_id) {

}
