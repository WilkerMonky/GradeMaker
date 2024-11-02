package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.TipoCursoDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.TipoCurso;
import com.datamonki.APIsCadastro.repository.TipoCursoRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class TipoCursoService {

	@Autowired
	private TipoCursoRepository tipoCursoRepository;

	private void verificarId(Integer id) {
		if (!tipoCursoRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	private void verificar(TipoCursoDto tipoCursoDto) {
		if (tipoCursoDto.nome().isEmpty()) {
			throw new ValidarException("Nome esta vazio");
		} else if (tipoCursoDto.nome().length() < 3) {
			throw new ValidarException("Nome deve estar acima de 3 caractere");
		}
	}

	@Transactional
	public ResponseEntity<ApiResponse> save(TipoCursoDto tipoCursoDto) {
		verificar(tipoCursoDto);
		TipoCurso tipoCurso = new TipoCurso();
		tipoCurso.setNome(tipoCursoDto.nome());
		 tipoCursoRepository.save(tipoCurso);
		 
		 return ResponseEntity.ok(new ApiResponse("Tipo do curso cadastrado com sucesso", tipoCurso)); 
		 
	}

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Tipo do curso localizado com sucesso", tipoCurso));
	}

	public ResponseEntity<ApiResponse> getAll() {
		List<TipoCurso> tiposCurso = tipoCursoRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de tipos de curso cadastrados", tiposCurso));
	}

	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, TipoCursoDto tipoCursoDto) {
		verificarId(id);
		verificar(tipoCursoDto);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCurso.setId(id);
		tipoCurso.setNome(tipoCursoDto.nome());
		tipoCursoRepository.save(tipoCurso);
		return ResponseEntity.ok(new ApiResponse("Tipo do curso alterado com sucesso", tipoCurso));
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCursoRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Tipo do curso deletado com sucesso", tipoCurso));
	}
}
