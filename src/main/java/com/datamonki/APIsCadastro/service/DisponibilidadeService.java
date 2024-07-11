package com.datamonki.APIsCadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisponibilidadeDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.model.DiaSemana;
import com.datamonki.APIsCadastro.model.Disponibilidade;
import com.datamonki.APIsCadastro.model.Professor;
import com.datamonki.APIsCadastro.model.Turno;
import com.datamonki.APIsCadastro.repository.DiaSemanaRepository;
import com.datamonki.APIsCadastro.repository.DisponibilidadeRepository;
import com.datamonki.APIsCadastro.repository.ProfessorRepository;
import com.datamonki.APIsCadastro.repository.TurnoRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class DisponibilidadeService {

	@Autowired
	private DisponibilidadeRepository disponibilidadeRepository;

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TurnoRepository turnoRepository;
	
	@Autowired
	private DiaSemanaRepository diaSemanaRepository;

	private void verificarId(Integer id) {
		if (!disponibilidadeRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}
	
	
	private void verificar(DisponibilidadeDto disponibilidadeDto) {
		Optional<Professor> professor = professorRepository.findById(disponibilidadeDto.professorId());
		Optional<DiaSemana> diaSemana = diaSemanaRepository.findById(disponibilidadeDto.diaSemanaId());
		Optional<Turno> turno = turnoRepository.findById(disponibilidadeDto.turnoId());
		
		if(professor.isEmpty()) {
			throw new IdNaoEncontradoException("Professor com ID " + disponibilidadeDto.professorId() + " não encontrado");
		}else if(diaSemana.isEmpty()) {
			throw new IdNaoEncontradoException("Dia da Semana com ID " + disponibilidadeDto.diaSemanaId() + " não encontrado");
		}else if(turno.isEmpty()) {
			throw new IdNaoEncontradoException("Turno com ID " + disponibilidadeDto.turnoId() + " não encontrado");
		}
		
		
	}
	

	@Transactional
	public ResponseEntity<ApiResponse> save(DisponibilidadeDto disponibilidadeDto) {
		verificar(disponibilidadeDto);
		Optional<Professor> professor = professorRepository.findById(disponibilidadeDto.professorId());
		Optional<DiaSemana> diaSemana = diaSemanaRepository.findById(disponibilidadeDto.diaSemanaId());
		Optional<Turno> turno = turnoRepository.findById(disponibilidadeDto.turnoId());
		Disponibilidade disponibilidade = new Disponibilidade();
		disponibilidade.setProfessor(professor.get());
		disponibilidade.setDiaSemana(diaSemana.get());
		disponibilidade.setTurno(turno.get());
		disponibilidadeRepository.save(disponibilidade);
	
		return ResponseEntity.ok(new ApiResponse("Cadastrado com sucesso", disponibilidade));
		
	}

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Disponibilidade localizada", disponibilidade));
	}
	
	
	public ResponseEntity<ApiResponse> getAll() {
		List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de Disponibilidade", disponibilidades));
	}

	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, DisponibilidadeDto disponibilidadeDto) {
		verificarId(id);
		verificar(disponibilidadeDto);
		Optional<Professor> professor = professorRepository.findById(disponibilidadeDto.professorId());
		Optional<DiaSemana> diaSemana = diaSemanaRepository.findById(disponibilidadeDto.diaSemanaId());
		Optional<Turno> turno = turnoRepository.findById(disponibilidadeDto.turnoId());
		Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).get();
		disponibilidade.setId(id);
		disponibilidade.setProfessor(professor.get());
		disponibilidade.setDiaSemana(diaSemana.get());
		disponibilidade.setTurno(turno.get());
		disponibilidadeRepository.save(disponibilidade);
		return ResponseEntity.ok(new ApiResponse("Atualizado com sucesso", disponibilidade));
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).get();
		disponibilidadeRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Deletado com sucesso", disponibilidade));
	}
}
