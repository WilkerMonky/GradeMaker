package com.datamonki.APIsCadastro.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Professor;
import com.datamonki.APIsCadastro.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	private void verificarId(Integer id) {
		if (!professorRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	private void verificar(ProfessorDto professorDto) {

		if (professorDto.nome().isBlank()) {
			throw new ValidarException("Nome esta vazio");
		} else if (professorDto.nome().length() < 3) {
			throw new ValidarException("Nome deve estar acima de 3 caractere");
		}
	}

	@Transactional
	public Professor save(ProfessorDto professorDto) {
		verificar(professorDto);
		Professor professor = new Professor();
		professor.setNome(professorDto.nome());
		return professorRepository.save(professor);

	}

	public Professor getById(Integer id) {
		verificarId(id);
		return professorRepository.findById(id).get();
	}

	public List<Professor> getAll() {
		return professorRepository.findAll();
	}

	@Transactional
	public Professor update(Integer id, ProfessorDto professorDto) {
		verificarId(id);
		verificar(professorDto);
		Professor professor = professorRepository.findById(id).get();
		professor.setId(id);
		professor.setNome(professorDto.nome());
		return professorRepository.save(professor);
	}

	public Professor delete(Integer id) {
		verificarId(id);
		Professor professor = professorRepository.findById(id).get();
		professorRepository.deleteById(id);
		return professor;
	}
}
