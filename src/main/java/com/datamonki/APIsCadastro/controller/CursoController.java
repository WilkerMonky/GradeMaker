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

import com.datamonki.APIsCadastro.dto.CursoDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.CursoService;


@RestController
@RequestMapping("/api/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody CursoDto cursoDto){
		try {
			return cursoService.save(cursoDto);
		} catch (ValidarException e) {
			return ResponseEntity.internalServerError().body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Curso, tente novamente", null));
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id){
		try {
			return cursoService.getById(id);
		} catch (IdNaoEncontradoException e) {
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Curso, tente novamente", null));
		}
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAll(){
		return cursoService.getAll();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
		try {
			return cursoService.delete(id);
		} catch (IdNaoEncontradoException e) {
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Curso, tente novamente", null));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody CursoDto cursoDto){
		try {
			return cursoService.update(id, cursoDto);
		} catch (IdNaoEncontradoException e) {
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			return ResponseEntity.internalServerError().body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Curso, tente novamente", null));
		}
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	