package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.model.DiaSemana;
import com.datamonki.APIsCadastro.repository.DiaSemanaRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;


@Service
public class DiaSemanaService {
	
	//Injeta o repositorio de dia da semana
	@Autowired
	DiaSemanaRepository diaSemanaRepository;

	//Retorna todas as dias da semana
	public ResponseEntity<ApiResponse> getAll(){
		List<DiaSemana> diasSemana = diaSemanaRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de dias da semana", diasSemana));

	}
	
	
}
