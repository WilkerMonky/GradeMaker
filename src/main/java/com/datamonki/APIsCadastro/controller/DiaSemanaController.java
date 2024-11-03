package com.datamonki.APIsCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.DiaSemanaService;

//Classe que representa o controller, responsavel pelas requisicoes de dia da semana para a api
@RestController
@RequestMapping("/api/dia_semana")
public class DiaSemanaController {

	//Injeta o service de dia da semana
	@Autowired
	 private DiaSemanaService diaSemanaService;
	
	//Faz a requisicao para buscar todos os dias da semana
	@GetMapping
	ResponseEntity<ApiResponse> getAll(){
		try {
			return diaSemanaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Dias da semana, tente novamente", null));
		}
	}
}
