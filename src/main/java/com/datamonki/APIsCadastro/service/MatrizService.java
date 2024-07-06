package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.MatrizDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Matriz;
import com.datamonki.APIsCadastro.repository.CursoRepository;
import com.datamonki.APIsCadastro.repository.MatrizRepository;

import jakarta.transaction.Transactional;

@Service
public class MatrizService {

	@Autowired
	private MatrizRepository matrizRepository;
	@Autowired
	private CursoRepository cursoRepository;

	public void verificarId(Integer id) {
		if (!matrizRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	public void verificar(Matriz matriz, MatrizDto matrizDto) {
		List<String> erros = new LinkedList<>();
		if (matriz.getNome().isEmpty()) {
			erros.add("Nome esta vazio");
		} else if (matriz.getNome().length() < 3) {
			erros.add("Nome deve estar acima de 3 caractere");
		}
		if (!cursoRepository.existsById(matrizDto.curso_id())) {
			erros.add("Nenhum Curso com id informado existe");
		}
		if (!erros.isEmpty()) {
			throw new ValidarException(erros);
		}
	}

	@Transactional
	public Matriz save(MatrizDto matrizDto) {
		Matriz matriz = new Matriz();
		matriz.setNome(matrizDto.nome());
		verificar(matriz, matrizDto);
		return matrizRepository.save(matriz);

	}

	public Matriz getById(Integer id) {
		verificarId(id);
		return matrizRepository.findById(id).get();
	}

	public List<Matriz> getAll() {
		return matrizRepository.findAll();
	}

	@Transactional
	public Matriz update(Integer id, MatrizDto matrizDto) {
		verificarId(id);
		Matriz matriz = matrizRepository.findById(id).get();
		matriz.setId(id);
		matriz.setNome(matrizDto.nome());
		verificar(matriz, matrizDto);
		return matrizRepository.save(matriz);
	}

	public Matriz delete(Integer id) {
		verificarId(id);
		Matriz matriz = matrizRepository.findById(id).get();
		matrizRepository.deleteById(id);
		return matriz;
	}
}
