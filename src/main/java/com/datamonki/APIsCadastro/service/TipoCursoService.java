package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.TipoCursoDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.TipoCurso;
import com.datamonki.APIsCadastro.repository.TipoCursoRepository;

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
	public TipoCurso save(TipoCursoDto tipoCursoDto) {
		TipoCurso tipoCurso = new TipoCurso();
		tipoCurso.setNome(tipoCursoDto.nome());
		verificarModelo(tipoCurso);
		return tipoCursoRepository.save(tipoCurso);

	}

	public TipoCurso getById(Integer id) {
		verificarId(id);
		return tipoCursoRepository.findById(id).get();
	}

	public List<TipoCurso> getAll() {
		return tipoCursoRepository.findAll();
	}

	@Transactional
	public TipoCurso update(Integer id, TipoCursoDto tipoCursoDto) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCurso.setId(id);
		tipoCurso.setNome(tipoCursoDto.nome());
		verificarModelo(tipoCurso);
		return tipoCursoRepository.save(tipoCurso);
	}

	public TipoCurso delete(Integer id) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCursoRepository.deleteById(id);
		return tipoCurso;
	}
}
