package com.datamonki.APIsCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.APIsCadastro.dto.DisciplinaDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.DisciplinaService;

//Classe que representa o controller, responsavel pelas requisicoes de disciplina para a api
@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaController {

	//Injeta o service de disciplina
	@Autowired
	private DisciplinaService disciplinaService;

	//Faz a requisicao para criar uma disciplina
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody DisciplinaDto disciplinaDto) {
		try {
			return disciplinaService.save(disciplinaDto);
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Disciplina, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar uma disciplina pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return disciplinaService.getById(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disciplina, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar todas as disciplinas
	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		try {
			return disciplinaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disciplinas, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar uma disciplina pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {
		try {
			return disciplinaService.getByNome(nome);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disciplina, tente novamente", null));
		}
	}

	//Faz a requisicao para deletar uma disciplina pelo id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return disciplinaService.delete(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Disciplina, tente novamente", null));
		}
	}

	//Faz a requisicao para atualizar uma disciplina pelo id
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody DisciplinaDto disciplinaDto) {
		try {
			return disciplinaService.update(id, disciplinaDto);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Disciplina, tente novamente", null));
		}
	}
}