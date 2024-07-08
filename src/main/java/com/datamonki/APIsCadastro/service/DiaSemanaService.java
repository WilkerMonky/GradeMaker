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
	
	@Autowired
	DiaSemanaRepository diaSemanaRepository;
	
	public ResponseEntity<ApiResponse> getAll(){
		List<DiaSemana> diasSemana = diaSemanaRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Dias da semana", diasSemana));

	}
	
	
}
