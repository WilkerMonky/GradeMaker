package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
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


	public void verificarId(Integer id) {
		if (!disciplinaRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	public void verificar(Disciplina disciplina, DisciplinaDto disciplinaDto) {
		List<String> erros = new LinkedList<>();
		if (disciplina.getNome().isBlank()) {
			erros.add("Nome esta vazio");
		} else if (disciplina.getNome().length() < 3) {
			erros.add("Nome deve estar acima de 3 caracteres.");
		}
		
		if (!erros.isEmpty()) {
			throw new ValidarException(erros);
		}
	}

	@Transactional
	public Disciplina save(DisciplinaDto disciplinaDto) {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(disciplinaDto.nome());
		verificar(disciplina, disciplinaDto);
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
		Disciplina disciplina = disciplinaRepository.findById(id).get();
		disciplina.setId(id);
		disciplina.setNome(disciplinaDto.nome());
		verificar(disciplina, disciplinaDto);
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina delete(Integer id) {
		verificarId(id);
		Disciplina disciplina = disciplinaRepository.findById(id).get();
		disciplinaRepository.deleteById(id);
		return disciplina;
	}
}
