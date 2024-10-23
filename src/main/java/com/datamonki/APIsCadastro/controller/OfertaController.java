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
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.response.ApiResponse;
import com.datamonki.APIsCadastro.service.OfertaService;

@RestController
@RequestMapping("/api/oferta")
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;

	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody OfertaDto ofertaDto) {
		try {
			return ofertaService.save(ofertaDto);
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel criar Oferta, tente novamente", null));
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
		try {
			return ofertaService.getById(id);

		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi localizar Oferta, tente novamente", null));
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		try {
			return ofertaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Ofertas, tente novamente", null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			return ofertaService.delete(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel deletar Oferta, tente novamente", null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody OfertaDto ofertaDto) {
		try {
			return ofertaService.update(id, ofertaDto);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Id não encontrado", null));
		} catch (ValidarException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError()
					.body(new ApiResponse("Entrada invalida, verifique e tente novamente", null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel editar Oferta, tente novamente", null));
		}
	}
}
