package com.datamonki.APIsCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.CursoDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.model.Curso;
import com.datamonki.APIsCadastro.repository.CursoRepository;
import com.datamonki.APIsCadastro.repository.TipoCursoRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;


@Service
public class CursoService {

	//Injeta o repositorio de curso
	@Autowired
	private CursoRepository cursoRepository;

	//Injeta o repositorio de tipo de curso
	@Autowired
	private TipoCursoRepository tipoCursoRepository;

	//Verifica se o id existe
	private void verificarId(Integer id) {
		if (!cursoRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel encontrar com o Id " + id + ", verifique e tente novamente"); 
		}
	}

	//Verifica se o nome esta vazio ou se tem menos de 3 caracteres
	private void verificar(CursoDto cursoDto) {
		List<String> messages = new ArrayList<>();
		if (cursoDto.nome().isBlank()) {
			messages.add("Nome esta vazio");
		} else if (cursoDto.nome().length() < 3) {
			messages.add("Nome deve estar acima de 3 caractere");
		}
		if (!tipoCursoRepository.existsById(cursoDto.tipoCursoId())) {
			messages.add("Nenhum Tipo Curso com id informado existe");
		}
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}

	//Verifica se o nome existe
	private void verificarNome(String nome) {
		List<Curso> cursos = cursoRepository.findByNomeContainingIgnoreCase(nome);
		if (cursos.isEmpty()) {
			throw new NameNotFoundException("Não foi possível encontrar curso com o nome " + nome + ", verifique e tente novamente");
		}
	}

	//Salva o curso 
	@Transactional
	public ResponseEntity<ApiResponse> save(CursoDto cursoDto) {
		verificar(cursoDto);
		Curso curso = new Curso();
		curso.setNome(cursoDto.nome());
		curso.setTipoCurso(tipoCursoRepository.findById(cursoDto.tipoCursoId()).get());
		cursoRepository.save(curso);
		return ResponseEntity.ok(new ApiResponse("Curso cadastrado com sucesso", curso)); 
	}

	//Retorna o curso pelo id
	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Curso curso =  cursoRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Curso localizado com sucesso", curso));
	}

	//Retorna todos os cursos
	public ResponseEntity<ApiResponse> getAll() {
		List<Curso> cursos  = cursoRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de cursos cadastrados", cursos));  
	}

	//Retorna os cursos pelo nome
	public ResponseEntity<ApiResponse> getByNome(String nome) {
		verificarNome(nome);
		List<Curso> cursos = cursoRepository.findByNomeContainingIgnoreCase(nome);
		return ResponseEntity.ok(new ApiResponse("Lista de cursos cadastrados", cursos));
	}

	//Atualiza o curso
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

	//Deleta o curso
	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Curso curso = cursoRepository.findById(id).get();
		cursoRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Curso deletado com sucesso", curso));
	}
	
}
