package com.datamonki.APIsCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.ProfessorService;

//Classe que representa o controller, responsavel pelas requisicoes de professor para a api
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	//Faz a requisicao para criar um professor
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse("Professor criado", professorService.save(professorDto)));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Professor, tente novamente", null));
		}

	}

	//Faz a requisicao para buscar um professor pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(new ApiResponse("Professor encontrado", professorService.getById(id)));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Professor, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar um professor pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {
		try {
			return ResponseEntity.ok(new ApiResponse("Professor encontrado", professorService.getByNome(nome)));
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Professor, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar todos os professores
	@GetMapping
	public ResponseEntity<ApiResponse> getAll() { 
		try {
			return ResponseEntity.ok(new ApiResponse("Lista de Professores", professorService.getAll()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Professores, tente novamente", null));
		}
	}

	//Faz a requisicao para deletar um professor pelo id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(new ApiResponse("Professor deletado", professorService.delete(id)));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Professor, tente novamente", null));
		}
	}

	//Faz a requisicao para atualizar um professor pelo id
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.ok(new ApiResponse("Professor editado", professorService.update(id, professorDto)));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse(e.getMessage(), null)); 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Professor, tente novamente", null));
		}
	}
}