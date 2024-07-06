package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisponibilidadeDto;
import com.datamonki.APIsCadastro.enums.DiaSemana;
import com.datamonki.APIsCadastro.enums.Turno;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Disponibilidade;

import com.datamonki.APIsCadastro.repository.DisponibilidadeRepository;
import com.datamonki.APIsCadastro.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class DisponibilidadeService {

	@Autowired
	private DisponibilidadeRepository disponibilidadeRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	public void verificarId(Integer id) {
		if (!disponibilidadeRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	public void verificar(Disponibilidade disponibilidade, DisponibilidadeDto disponibilidadeDto) {
		List<String> erros = new LinkedList<>();
		if (!professorRepository.existsById(disponibilidadeDto.professor_id())) {
			erros.add("Nenhum Professor com id informado existe");
		}
		if (DiaSemana.retornar_dia_semana(disponibilidadeDto.dia_semana_id()) == null) {
			erros.add("Nenhum Dia da Semana com id informado existe");
		}
		if (Turno.retornar_turno(disponibilidadeDto.dia_semana_id()) == null) {
			erros.add("Nenhum Turno com id informado existe");
		}
		
		if (!erros.isEmpty()) {
			throw new ValidarException(erros);			
		}
	}

	@Transactional
	public Disponibilidade save(DisponibilidadeDto disponibilidadeDto) {
		Disponibilidade disponibilidade = new Disponibilidade();
		disponibilidade.setProfessor(professorRepository.findById(disponibilidadeDto.professor_id()).get());
		disponibilidade.setDiaSemana(DiaSemana.retornar_dia_semana(disponibilidadeDto.dia_semana_id()));
		disponibilidade.setTurno(Turno.retornar_turno(disponibilidadeDto.dia_semana_id()));
		verificar(disponibilidade, disponibilidadeDto);
		return disponibilidadeRepository.save(disponibilidade);

	}

	public Disponibilidade getById(Integer id) {
		verificarId(id);
		return disponibilidadeRepository.findById(id).get();
	}

	public List<Disponibilidade> getAll() {
		return disponibilidadeRepository.findAll();
	}

	@Transactional
	public Disponibilidade update(Integer id, DisponibilidadeDto disponibilidadeDto) {
		verificarId(id);
		Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).get();
		disponibilidade.setId(id);
		disponibilidade.setProfessor(professorRepository.findById(disponibilidadeDto.professor_id()).get());
		disponibilidade.setDiaSemana(DiaSemana.retornar_dia_semana(disponibilidadeDto.dia_semana_id()));
		disponibilidade.setTurno(Turno.retornar_turno(disponibilidadeDto.dia_semana_id()));
		verificar(disponibilidade, disponibilidadeDto);
		return disponibilidadeRepository.save(disponibilidade);
	}

	public Disponibilidade delete(Integer id) {
		verificarId(id);
		Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).get();
		disponibilidadeRepository.deleteById(id);
		return disponibilidade;
	}
}
