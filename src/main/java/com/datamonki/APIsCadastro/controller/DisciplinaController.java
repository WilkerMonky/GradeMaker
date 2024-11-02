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
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.DisciplinaService;

@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;

	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody DisciplinaDto disciplinaDto) {
		try {
			return disciplinaService.save(disciplinaDto);
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Disciplina, tente novamente", null));
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return disciplinaService.getById(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disciplina, tente novamente", null));
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		try {
			return disciplinaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disciplinas, tente novamente", null));
		}
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {
		try {
			return disciplinaService.getByNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disciplina, tente novamente", null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return disciplinaService.delete(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Disciplina, tente novamente", null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody DisciplinaDto disciplinaDto) {
		try {
			return disciplinaService.update(id, disciplinaDto);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Disciplina, tente novamente", null));
		}
	}
}
