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
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.DisponibilidadeService;

//Classe que representa o controller, responsavel pelas requisicoes de disponibilidade para a api
@RestController
@RequestMapping("/api/disponibilidade")
public class DisponibilidadeController {

	//Injeta o service de disponibilidade
	@Autowired
	private DisponibilidadeService disponibilidadeService;

	//Faz a requisicao para criar uma disponibilidade
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody DisponibilidadeDto disponibilidadeDto) {
		try {
			return disponibilidadeService.save(disponibilidadeDto);
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Disponibilidade, tente novamente", null));
		}

	}

	//Faz a requisicao para buscar uma disponibilidade pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return disponibilidadeService.getById(id);

		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Disponibilidade, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar todas as disponibilidades
	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		try {
			return disponibilidadeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disponibilidades, tente novamente", null));
		}
	}

	//Faz a requisicao para deletar uma disponibilidade pelo id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return disponibilidadeService.delete(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.ok(new ApiResponse("Não foi possivel deletar Disponibilidade, tente novamente", null));
		}
	}

	//Faz a requisicao para atualizar uma disponibilidade pelo id
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id,
			@RequestBody DisponibilidadeDto disponibilidadeDto) {
		try {
			return disponibilidadeService.update(id, disponibilidadeDto);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Disponibilidade, tente novamente", null));
		}
	}
	
	//Faz a requisicao para verificar a disponibilidade de um professor pelo id
	@GetMapping("verificarProfessor/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id){
		try {
			return disponibilidadeService.verifyDisponibilidadeProfessor(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Disponibilidade, tente novamente", null));
		}
	}
}