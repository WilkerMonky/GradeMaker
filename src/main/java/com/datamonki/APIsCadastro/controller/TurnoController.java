package com.datamonki.APIsCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.TurnoService;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {

	@Autowired
	private TurnoService turnoService;

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		try {
			return turnoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Dias da semana, tente novamente", null));
		}
	}
}
