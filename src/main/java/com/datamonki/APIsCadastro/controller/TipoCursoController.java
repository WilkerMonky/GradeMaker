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

import com.datamonki.APIsCadastro.dto.TipoCursoDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.TipoCursoService;

//Classe que representa o controller, responsavel pelas requisicoes de tipo de curso para a api
@RestController
@RequestMapping("/api/tipo_curso")
public class TipoCursoController {

	@Autowired
	private TipoCursoService tipoCursoService;

	//Faz a requisicao para criar um tipo de curso
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody TipoCursoDto tipoCursoDto) {
		try {
			return tipoCursoService.save(tipoCursoDto);
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Tipo Curso, tente novamente", null));
		}

	}

	//Faz a requisicao para buscar um tipo de curso pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return tipoCursoService.getById(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Tipo Curso, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar todos os tipos de cursos
	@GetMapping
	public ResponseEntity<ApiResponse> getAll() { 
		try {
			return tipoCursoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Tipos de cursos, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar um tipo de curso pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {
		try {
			return tipoCursoService.getByNome(nome);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Tipo Curso, tente novamente", null));
		}
	}

	//Faz a requisicao para deletar um tipo de curso pelo id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return tipoCursoService.delete(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Tipo Curso, tente novamente", null));
		}
	}

	//Faz a requisicao para atualizar um tipo de curso pelo id
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody TipoCursoDto tipoCursoDto) {
		try {
			return tipoCursoService.update(id, tipoCursoDto);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Tipo Curso, tente novamente", null));
		}
	}
}