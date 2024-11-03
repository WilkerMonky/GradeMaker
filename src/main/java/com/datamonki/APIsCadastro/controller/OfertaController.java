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

import com.datamonki.APIsCadastro.dto.OfertaDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.OfertaService;

//Classe que representa o controller, responsavel pelas requisicoes de oferta para a api
@RestController
@RequestMapping("/api/oferta")
public class OfertaController {

	//Injeta o service de oferta
	@Autowired
	private OfertaService ofertaService;

	//Faz a requisicao para criar uma oferta
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody OfertaDto ofertaDto) {
		try {
			return ofertaService.save(ofertaDto);
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Oferta, tente novamente", null));
		}

	}

	//Faz a requisicao para buscar uma oferta pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return ofertaService.getById(id);

		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Oferta, tente novamente", null));
		}
	}

	//Faz a requisicao para buscar todas as ofertas
	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		try {
			return ofertaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Ofertas, tente novamente", null));
		}
	}

	//Faz a requisicao para deletar uma oferta pelo id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return ofertaService.delete(id);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Oferta, tente novamente", null));
		}
	}

	//Faz a requisicao para atualizar uma oferta pelo id
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody OfertaDto ofertaDto) {
		try {
			return ofertaService.update(id, ofertaDto);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Oferta, tente novamente", null));
		}
	}
}