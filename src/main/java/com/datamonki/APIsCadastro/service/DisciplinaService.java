package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisciplinaDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Disciplina;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;


	private void verificarId(Integer id) {
		if (!disciplinaRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	private void verificar(DisciplinaDto disciplinaDto) { 
		if (disciplinaDto.nome().isBlank()) {
			throw new ValidarException("Nome esta vazio");
		} else if (disciplinaDto.nome().length() < 3) {
			throw new ValidarException("Nome deve estar acima de 3 caracteres.");
		}
		

	}

	@Transactional
	public ResponseEntity<ApiResponse> save(DisciplinaDto disciplinaDto) {
		verificar(disciplinaDto);
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(disciplinaDto.nome());
		disciplinaRepository.save(disciplina);
		return ResponseEntity.ok(new ApiResponse("Disciplina cadastrada com sucesso", disciplinaDto));
	}

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Disciplina disciplina =  disciplinaRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Disciplina localizada com sucesso", disciplina));
	}

	public  ResponseEntity<ApiResponse> getAll() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de disciplinas cadastradas", disciplinas));
	}

	public ResponseEntity<ApiResponse> getByNome(String nome) {
		List<Disciplina> disciplinas = disciplinaRepository.findByNomeContainingIgnoreCase(nome);
		return ResponseEntity.ok(new ApiResponse("Lista de disciplinas cadastradas", disciplinas));
	}

	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, DisciplinaDto disciplinaDto) {
		verificarId(id);
		verificar(disciplinaDto);
		Disciplina disciplina = disciplinaRepository.findById(id).get();
		disciplina.setId(id);
		disciplina.setNome(disciplinaDto.nome());
		disciplinaRepository.save(disciplina);
		return ResponseEntity.ok(new ApiResponse("Disciplina atualizada com sucesso", disciplinaDto)); 
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Disciplina disciplina = disciplinaRepository.findById(id).get();
		DisciplinaDto d2 = new DisciplinaDto(disciplina.getNome());
		disciplinaRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Disciplina deletada com sucesso", d2)); 
	}
}
