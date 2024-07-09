package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
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

	public void verificarId(Integer id) {
		if (!tipoCursoRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	public void verificarModelo(TipoCurso tipoCurso) {
		List<String> erros = new LinkedList<>();
		if (tipoCurso.getNome().isEmpty()) {
			erros.add("Nome esta vazio");
		} else if (tipoCurso.getNome().length() < 3) {
			erros.add("Nome deve estar acima de 3 caractere");
		}
		if (!erros.isEmpty()) {
			throw new ValidarException(erros);
		}
	}

	@Transactional
	public ResponseEntity<ApiResponse> save(TipoCursoDto tipoCursoDto) {
		TipoCurso tipoCurso = new TipoCurso();
		tipoCurso.setNome(tipoCursoDto.nome());
		verificarModelo(tipoCurso);
		 tipoCursoRepository.save(tipoCurso);
		 
		 return ResponseEntity.ok(new ApiResponse("Tipo do curso criado", tipoCurso));
		 
	}

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Localizado", tipoCurso));
	}

	public ResponseEntity<ApiResponse> getAll() {
		List<TipoCurso> tiposCurso = tipoCursoRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista tipos de curso", tiposCurso));
	}

	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, TipoCursoDto tipoCursoDto) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCurso.setId(id);
		tipoCurso.setNome(tipoCursoDto.nome());
		verificarModelo(tipoCurso);
		tipoCursoRepository.save(tipoCurso);
		return ResponseEntity.ok(new ApiResponse("Alterado com sucesso", tipoCurso));
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCursoRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Deletado com sucesso", tipoCurso));
	}
}
