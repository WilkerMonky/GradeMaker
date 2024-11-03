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

import com.datamonki.APIsCadastro.dto.CursoDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.CursoService;

//Classe que representa o controller, responsavel pelas requisicoes de curso para a api
@RestController
@RequestMapping("/api/curso" )
public class CursoController {
	
	//Injeta o service de curso
	@Autowired
	private CursoService cursoService;
	
	//Faz a requisicao para criar um novo curso
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody CursoDto cursoDto){
		try {
			return cursoService.save(cursoDto);
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Curso, tente novamente", null));
		}
		
	}
	
	//Faz a requisicao para buscar um curso pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id){
		try {
			return cursoService.getById(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Curso, tente novamente", null));
		}
	}
	
	//Faz a requisicao para buscar todos os cursos
	@GetMapping
	public ResponseEntity<ApiResponse> getAll(){ 
		try {
			return cursoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Cursos, tente novamente", null));
		}
	}
	
	//Faz a requisicao para buscar um curso pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {
		try {
			return cursoService.getByNome(nome);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Cursos, tente novamente", null));
		}
	}
	
	//Faz a requisicao para deletar um curso pelo id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
		try {
			return cursoService.delete(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Curso, tente novamente", null));
		}
	}
	
	//Faz a requisicao para atualizar um curso pelo id
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody CursoDto cursoDto){
		try {
			return cursoService.update(id, cursoDto);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Curso, tente novamente", null));
		}
	}
}