package com.datamonki.APIsCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisciplinaDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.model.Disciplina;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class DisciplinaService {

	//Injeta o repositorio de disciplina
	@Autowired
	private DisciplinaRepository disciplinaRepository;

	//Verifica se o id existe
	private void verificarId(Integer id) {
		if (!disciplinaRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel encontrar com o Id " + id + ", verifique e tente novamente"); 
		}
	}

	//Verifica se o nome esta vazio ou se tem menos de 3 caracteres
	private void verificar(DisciplinaDto disciplinaDto) { 
		List<String> messages = new ArrayList<>();
		if (disciplinaDto.nome().isBlank()) {
			messages.add("Nome esta vazio");
		} else if (disciplinaDto.nome().length() < 3) {
			messages.add("Nome deve estar acima de 3 caracteres.");
		}
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}
	private void verificarNome(String nome) {
		List<Disciplina> disciplinas = disciplinaRepository.findByNomeContainingIgnoreCase(nome);
		if (disciplinas.isEmpty()) {
			throw new NameNotFoundException("Não foi possível encontrar disciplina com o nome " + nome + ", verifique e tente novamente");
		}
	}

	//Salva a disciplina
	@Transactional
	public ResponseEntity<ApiResponse> save(DisciplinaDto disciplinaDto) {
		verificar(disciplinaDto);
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(disciplinaDto.nome());
		disciplinaRepository.save(disciplina);
		return ResponseEntity.ok(new ApiResponse("Disciplina cadastrada com sucesso", disciplinaDto));
	}

	//Retorna a disciplina pelo id
	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Disciplina disciplina =  disciplinaRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Disciplina localizada com sucesso", disciplina));
	}

	//Retorna todas as disciplinas
	public  ResponseEntity<ApiResponse> getAll() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de disciplinas cadastradas", disciplinas));
	}

	//Retorna as disciplinas pelo nome
	public ResponseEntity<ApiResponse> getByNome(String nome) {
		verificarNome(nome);
		List<Disciplina> disciplinas = disciplinaRepository.findByNomeContainingIgnoreCase(nome);
		return ResponseEntity.ok(new ApiResponse("Lista de disciplinas cadastradas", disciplinas));
	}

	//Atualiza a disciplina
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

	//Deleta a disciplina
	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Disciplina disciplina = disciplinaRepository.findById(id).get();
		DisciplinaDto d2 = new DisciplinaDto(disciplina.getNome());
		disciplinaRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Disciplina deletada com sucesso", d2)); 
	}
}
