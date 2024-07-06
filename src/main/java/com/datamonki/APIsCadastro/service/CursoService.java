package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.CursoDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Curso;
import com.datamonki.APIsCadastro.repository.CursoRepository;
import com.datamonki.APIsCadastro.repository.TipoCursoRepository;

import jakarta.transaction.Transactional;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private TipoCursoRepository tipoCursoRepository;

	public void verificarId(Integer id) {
		if (!cursoRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	public void verificar(Curso curso, CursoDto cursoDto) {
		List<String> erros = new LinkedList<>();
		if (curso.getNome().isBlank()) {
			erros.add("Nome esta vazio");
		} else if (curso.getNome().length() < 3) {
			erros.add("Nome deve estar acima de 3 caractere");
		}
		if (!tipoCursoRepository.existsById(cursoDto.tipo_curso_id())) {
			erros.add("Nenhum Tipo Curso com id informado existe");
		}
		if (!erros.isEmpty()) {
			throw new ValidarException(erros);
		}
	}

	@Transactional
	public Curso save(CursoDto cursoDto) {
		Curso curso = new Curso();
		curso.setNome(cursoDto.nome());
		curso.setTipoCurso(tipoCursoRepository.findById(cursoDto.tipo_curso_id()).get());
		verificar(curso, cursoDto);
		return cursoRepository.save(curso);

	}

	public Curso getById(Integer id) {
		verificarId(id);
		return cursoRepository.findById(id).get();
	}

	public List<Curso> getAll() {
		return cursoRepository.findAll();
	}

	@Transactional
	public Curso update(Integer id, CursoDto cursoDto) {
		verificarId(id);
		Curso curso = cursoRepository.findById(id).get();
		curso.setId(id);
		curso.setNome(cursoDto.nome());
		curso.setTipoCurso(tipoCursoRepository.findById(cursoDto.tipo_curso_id()).get());
		verificar(curso, cursoDto);
		return cursoRepository.save(curso);
	}

	public Curso delete(Integer id) {
		verificarId(id);
		Curso curso = cursoRepository.findById(id).get();
		cursoRepository.deleteById(id);
		return curso;
	}
}
