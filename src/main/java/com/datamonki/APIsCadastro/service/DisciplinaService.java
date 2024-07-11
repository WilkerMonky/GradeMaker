package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisciplinaDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Disciplina;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;

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
	public Disciplina save(DisciplinaDto disciplinaDto) {
		verificar(disciplinaDto);
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(disciplinaDto.nome());
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina getById(Integer id) {
		verificarId(id);
		return disciplinaRepository.findById(id).get();
	}

	public List<Disciplina> getAll() {
		return disciplinaRepository.findAll();
	}

	@Transactional
	public Disciplina update(Integer id, DisciplinaDto disciplinaDto) {
		verificarId(id);
		verificar(disciplinaDto);
		Disciplina disciplina = disciplinaRepository.findById(id).get();
		disciplina.setId(id);
		disciplina.setNome(disciplinaDto.nome());
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina delete(Integer id) {
		verificarId(id);
		Disciplina disciplina = disciplinaRepository.findById(id).get();
		disciplinaRepository.deleteById(id);
		return disciplina;
	}
}
