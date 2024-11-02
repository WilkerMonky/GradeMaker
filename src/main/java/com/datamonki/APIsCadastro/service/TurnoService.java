package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.model.Turno;
import com.datamonki.APIsCadastro.repository.TurnoRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

@Service
public class TurnoService {
	@Autowired
	TurnoRepository turnoRepository;
	
	public ResponseEntity<ApiResponse> getAll(){
		List<Turno> turnos  = turnoRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de turnos cadastrados", turnos));
	}
}
