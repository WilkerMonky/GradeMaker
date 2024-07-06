package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotNull;

public record OfertaDto(@NotNull Integer semestre,
						@NotNull Integer matriz_id,
						@NotNull Integer disciplina_id) {

}
