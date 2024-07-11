package com.datamonki.APIsCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.ProfessorDisciplinaId;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.ProfessorDisciplinaService;

@RestController
@RequestMapping(path ="/api/professor_disciplina")
public class ProfessorDisciplinaController {
	
	@Autowired
	ProfessorDisciplinaService professorDisciplinaService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody ProfessorDisciplinaId professorDisciplinaId){
		try {
				return professorDisciplinaService.save(professorDisciplinaId);
			} catch (ValidarException e) {
				return ResponseEntity.internalServerError().body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok(new ApiResponse("Não foi possivel criar o relacionamento, tente novamente.", null));
			}
				
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAll()
	{
		try { 
			return professorDisciplinaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel localizar os relacionamentos, tente novamente.", null));
		}
	}
	
	@DeleteMapping
	public ResponseEntity<ApiResponse> delete(@RequestBody ProfessorDisciplinaId professorDisciplinaId){
		try {
			return professorDisciplinaService.delete(professorDisciplinaId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar o relacionamento, tente novamente.", null));
		}
	}
/*

 * 
 * */
}