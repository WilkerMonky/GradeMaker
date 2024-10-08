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
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse("Professor criado", professorService.save(professorDto)));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Professor, tente novamente", null));
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(new ApiResponse("Professor encontrado", professorService.getById(id)));
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Professor, tente novamente", null));
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		return ResponseEntity.ok(new ApiResponse("Lista de Professores", professorService.getAll()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(new ApiResponse("Professor deletado", professorService.delete(id)));
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Professor, tente novamente", null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.ok(new ApiResponse("Professor editado", professorService.update(id, professorDto)));
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Professor, tente novamente", null));
		}
	}
}
