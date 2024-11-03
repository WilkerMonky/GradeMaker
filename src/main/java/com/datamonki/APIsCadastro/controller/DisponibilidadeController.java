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

import com.datamonki.APIsCadastro.dto.DisponibilidadeDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.DisponibilidadeService;

@RestController
@RequestMapping("/api/disponibilidade")
public class DisponibilidadeController {

	@Autowired
	private DisponibilidadeService disponibilidadeService;

	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody DisponibilidadeDto disponibilidadeDto) {
		try {
			return disponibilidadeService.save(disponibilidadeDto);
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Disponibilidade, tente novamente", null));
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return disponibilidadeService.getById(id);

		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Disponibilidade, tente novamente", null));
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		try {
			return disponibilidadeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disponibilidades, tente novamente", null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return disponibilidadeService.delete(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.ok(new ApiResponse("Não foi possivel deletar Disponibilidade, tente novamente", null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id,
			@RequestBody DisponibilidadeDto disponibilidadeDto) {
		try {
			return disponibilidadeService.update(id, disponibilidadeDto);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Disponibilidade, tente novamente", null));
		}
	}
	@GetMapping("verificarProfessor/{id}")
	public ResponseEntity<ApiResponse> verifyDispProfessor(@PathVariable Integer id){
		try {
			return disponibilidadeService.verifyDisponibilidadeProfessor(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Disponibilidade, tente novamente", null));
		}
	}
	@GetMapping("professor/{id}")
	public ResponseEntity<ApiResponse> getByIdProfessor(@PathVariable Integer id){
		try {
			return disponibilidadeService.getByIdProfessor(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Disponibilidade, tente novamente", null));
		}
	}
	@DeleteMapping("professor/{id}")
	public ResponseEntity<ApiResponse> deleteByIdProfessor(@PathVariable Integer id){
		try {
			return disponibilidadeService.deletetByIdProfessor(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Disponibilidade, tente novamente", null));
		}
	}

}
