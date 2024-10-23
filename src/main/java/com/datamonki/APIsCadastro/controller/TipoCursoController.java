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
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.TipoCursoService;

@RestController
@RequestMapping("/api/tipo_curso")
public class TipoCursoController {

	@Autowired
	private TipoCursoService tipoCursoService;

	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody TipoCursoDto tipoCursoDto) {
		try {
			return tipoCursoService.save(tipoCursoDto);
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Tipo Curso, tente novamente", null));
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return tipoCursoService.getById(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Tipo Curso, tente novamente", null));
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() { 
		try {
			return tipoCursoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Tipos de cursos, tente novamente", null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return tipoCursoService.delete(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Tipo Curso, tente novamente", null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody TipoCursoDto tipoCursoDto) {
		try {
			return tipoCursoService.update(id, tipoCursoDto);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Tipo Curso, tente novamente", null));
		}
	}
}
