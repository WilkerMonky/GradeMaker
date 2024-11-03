package com.datamonki.APIsCadastro.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.model.Professor;
import com.datamonki.APIsCadastro.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service 
public class ProfessorService {

	//Injeta o repositorio de professor
	@Autowired
	private ProfessorRepository professorRepository;

	//Verifica se o id existe
	private void verificarId(Integer id) {
		if (!professorRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel encontrar com o Id " + id + ", verifique e tente novamente"); 	
		}
	}

	//Verifica se o nome esta vazio ou se tem menos de 3 caracteres
	private void verificar(ProfessorDto professorDto) {
		List<String> messages = new ArrayList<>();
		if (professorDto.nome().isBlank()) {
			messages.add("Nome esta vazio");
		} else if (professorDto.nome().length() < 3) {
			messages.add("Nome deve estar acima de 3 caractere");
		}
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}

	//Verifica se o nome existe
	private void verificarNome(String nome) {
		List<Professor> professores = professorRepository.findByNomeContainingIgnoreCase(nome); 
		if (professores.isEmpty()) {
			throw new NameNotFoundException("Não foi possível encontrar professor com o nome " + nome + ", verifique e tente novamente");
		}
	}

	//Salva o professor
	@Transactional
	public Professor save(ProfessorDto professorDto) {
		verificar(professorDto);
		Professor professor = new Professor();
		professor.setNome(professorDto.nome());
		return professorRepository.save(professor);

	}

	//Retorna o professor pelo id
	public Professor getById(Integer id) {
		verificarId(id);
		return professorRepository.findById(id).get();
	}

	//Retorna todos os professores
	public List<Professor> getAll() {
		return professorRepository.findAll();
	}

	//Retorna os professores pelo nome
	public List<Professor> getByNome(String nome) {
		verificarNome(nome);
		return professorRepository.findByNomeContainingIgnoreCase(nome);
	}

	//Atualiza o professor
	@Transactional
	public Professor update(Integer id, ProfessorDto professorDto) {
		verificarId(id);
		verificar(professorDto);
		Professor professor = professorRepository.findById(id).get();
		professor.setId(id);
		professor.setNome(professorDto.nome());
		return professorRepository.save(professor);
	}

	//Deleta o professor
	public Professor delete(Integer id) {
		verificarId(id);
		Professor professor = professorRepository.findById(id).get();
		professorRepository.deleteById(id);
		return professor;
	}
}
