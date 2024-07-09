package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotNull;

public record OfertaDto(@NotNull Integer semestre,
						@NotNull Integer matrizId,
						@NotNull Integer disciplinaId) {

}
