package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.CursoDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Curso;
import com.datamonki.APIsCadastro.repository.CursoRepository;
import com.datamonki.APIsCadastro.repository.TipoCursoRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private TipoCursoRepository tipoCursoRepository;

	private void verificarId(Integer id) {
		if (!cursoRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	private void verificar(CursoDto cursoDto) {
		if (cursoDto.nome().isBlank()) {
			throw new ValidarException("Nome esta vazio");
		} else if (cursoDto.nome().length() < 3) {
			throw new ValidarException("Nome deve estar acima de 3 caractere");
		}
		if (!tipoCursoRepository.existsById(cursoDto.tipoCursoId())) {
			throw new ValidarException("Nenhum Tipo Curso com id informado existe");
		}
	}

	@Transactional
	public ResponseEntity<ApiResponse> save(CursoDto cursoDto) {
		verificar(cursoDto);
		Curso curso = new Curso();
		curso.setNome(cursoDto.nome());
		curso.setTipoCurso(tipoCursoRepository.findById(cursoDto.tipoCursoId()).get());
		cursoRepository.save(curso);
		return ResponseEntity.ok(new ApiResponse("Curso criado com sucesso", curso));
	}

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Curso curso =  cursoRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Curso localizado", curso));
	}

	public ResponseEntity<ApiResponse> getAll() {
		List<Curso> cursos  = cursoRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de cursos", cursos));
	}

	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, CursoDto cursoDto) {
		verificarId(id);
		verificar(cursoDto);
		Curso curso = cursoRepository.findById(id).get();
		curso.setId(id);
		curso.setNome(cursoDto.nome());
		curso.setTipoCurso(tipoCursoRepository.findById(cursoDto.tipoCursoId()).get());
		cursoRepository.save(curso);
		return ResponseEntity.ok(new ApiResponse("Curso alterado com sucesso", curso));
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Curso curso = cursoRepository.findById(id).get();
		cursoRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Curso deletado com sucesso", curso));
	}
	
}
