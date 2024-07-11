package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.MatrizDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Curso;
import com.datamonki.APIsCadastro.model.Matriz;
import com.datamonki.APIsCadastro.repository.CursoRepository;
import com.datamonki.APIsCadastro.repository.MatrizRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class MatrizService {

	@Autowired
	private MatrizRepository matrizRepository;
	@Autowired
	private CursoRepository cursoRepository;

	private void verificarId(Integer id) {
		if (!matrizRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	private void verificar(MatrizDto matrizDto) {
		if (matrizDto.nome().isEmpty()) {
			throw new ValidarException("Nome esta vazio");
		} else if (matrizDto.nome().length() < 3) {
			throw new ValidarException("Nome deve estar acima de 3 caractere");
		}
		if (!cursoRepository.existsById(matrizDto.cursoId())) {
			throw new ValidarException("Nenhum Curso com id informado existe");
		}
	}

	@Transactional
	public ResponseEntity<ApiResponse> save(MatrizDto matrizDto) {
		verificar(matrizDto);
		Matriz matriz = new Matriz();
		matriz.setNome(matrizDto.nome());
		Curso curso = cursoRepository.findById(matrizDto.cursoId()).get();
		matriz.setCurso(curso);
		matrizRepository.save(matriz);
		return ResponseEntity.ok(new ApiResponse("Matriz criada com sucesso",matriz));
	}

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Matriz matriz =  matrizRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Matriz localizada",matriz)); 
	}

	public  ResponseEntity<ApiResponse> getAll() {
		List<Matriz> matrizes = matrizRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de matrizes",matrizes)); 
	}

	@Transactional
	public ResponseEntity<ApiResponse>  update(Integer id, MatrizDto matrizDto) {
		verificarId(id);
		verificar(matrizDto);
		Matriz matriz = matrizRepository.findById(id).get();
		matriz.setId(id);
		matriz.setNome(matrizDto.nome());
		Curso curso = cursoRepository.findById(matrizDto.cursoId()).get();
		matriz.setCurso(curso);
		matrizRepository.save(matriz);
		return ResponseEntity.ok(new ApiResponse("Matriz atualizada com sucesso", matriz)); 
		
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Matriz matriz = matrizRepository.findById(id).get();
		matrizRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Matriz deletada com sucesso",matriz)); 
	}
}
