package com.datamonki.APIsCadastro.dto;

import jakarta.validation.constraints.NotNull;

//Classe que representa o dto(data transfer object) de oferta para a api
public record OfertaDto(@NotNull Integer semestre, @NotNull Integer disciplinaId, @NotNull Integer cursoId) {

}