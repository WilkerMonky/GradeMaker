package com.datamonki.APIsCadastro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisponibilidadeDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
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

	//Injeta o repositorio de disponibilidade	
	@Autowired
	private DisponibilidadeRepository disponibilidadeRepository;

	//Injeta o repositorio de professor
	@Autowired
	private ProfessorRepository professorRepository;
	
	//Injeta o repositorio de turno
	@Autowired
	private TurnoRepository turnoRepository;
	
	//Injeta o repositorio de dia da semana
	@Autowired
	private DiaSemanaRepository diaSemanaRepository;

	//Verifica se o id existe
	private void verificarId(Integer id) {
		if (!disponibilidadeRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel encontrar com o Id " + id + ", verifique e tente novamente");
		}
	}

	private void verificarProfessorId(Integer id) {
		if (!professorRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel  encontrar  um professor com o Id " + id + ", verifique e tente novamente");
		}
	}

	//Verifica se o professor, dia da semana e turno existe
	private void verificar(DisponibilidadeDto disponibilidadeDto) {
		List<String> messages = new ArrayList<>();
		Optional<Professor> professor = professorRepository.findById(disponibilidadeDto.professorId());
		Optional<DiaSemana> diaSemana = diaSemanaRepository.findById(disponibilidadeDto.diaSemanaId());
		Optional<Turno> turno = turnoRepository.findById(disponibilidadeDto.turnoId());
		
		if(professor.isEmpty()) {
			messages.add("Professor com ID " + disponibilidadeDto.professorId() + " não encontrado");
		}else if(diaSemana.isEmpty()) {
			messages.add("Dia da Semana com ID " + disponibilidadeDto.diaSemanaId() + " não encontrado");
		}else if(turno.isEmpty()) {
			messages.add("Turno com ID " + disponibilidadeDto.turnoId() + " não encontrado");
		}else if (disponibilidadeRepository.verifyRepeticao(disponibilidadeDto.professorId(),
				disponibilidadeDto.diaSemanaId(), disponibilidadeDto.turnoId(), disponibilidadeDto.semestre(), disponibilidadeDto.ano())){
				messages.add("Disponibilidade já agendada");
		}
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}


	}

	//Salva a disponibilidade
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
		disponibilidade.setSemestre(disponibilidadeDto.semestre());
		disponibilidade.setAno(disponibilidadeDto.ano());
		disponibilidadeRepository.save(disponibilidade);
	
		return ResponseEntity.ok(new ApiResponse("Disponibilidade cadastrada com sucesso", disponibilidade));
		
	}

	//Retorna a disponibilidade pelo id
	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Disponibilidade localizada com sucesso", disponibilidade));
	}
	
	
	//Retorna todas as disponibilidades
	public ResponseEntity<ApiResponse> getAll() {
		List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de disponibilidades cadastradas", disponibilidades));
	}

	//Atualiza a disponibilidade
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
		disponibilidade.setSemestre(disponibilidadeDto.semestre()); 
		disponibilidade.setAno(disponibilidadeDto.ano());
		disponibilidadeRepository.save(disponibilidade);
		return ResponseEntity.ok(new ApiResponse("Disponibilidade atualizada com sucesso", disponibilidade));
	}

	//Deleta a disponibilidade
	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).get();
		disponibilidadeRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Disponibilidade deletada com sucesso", disponibilidade));
	}

	//Verifica se o professor tem disponibilidade
	public ResponseEntity<ApiResponse> verifyDisponibilidadeProfessor (Integer professorId){
		Integer numRegistros = disponibilidadeRepository.getNumDisponibilidadeProfessor(professorId);
		return  ResponseEntity.ok(new ApiResponse("Sucesso! Número de disponibilidades para o professor selecionado", numRegistros));
	}

	public ResponseEntity<ApiResponse> getByIdProfessor(Integer professorId) {
		verificarProfessorId(professorId);
		List<Object[]> results = disponibilidadeRepository.findDisponibilidadeByProfessorId(professorId);
		List<DisponibilidadeDto> listaDisponibilidade = results.stream().map(result -> new DisponibilidadeDto(
				(Integer) result[0],  // id
				(Integer) result[1],  // ano
				(Integer) result[2],  // semestre
				(Integer) result[3],  // diaSemanaId
				(Integer) result[4]   // turnoId
		)).collect(Collectors.toList());
		return ResponseEntity.ok(new ApiResponse("Disponibilidade localizada com sucesso", listaDisponibilidade));
	}

	public ResponseEntity<ApiResponse> deleteByIdProfessor (Integer professorId){
		verificarProfessorId(professorId);
		if (disponibilidadeRepository.verifyDisponibilidadeProfessor(professorId)){
			disponibilidadeRepository.deleteByIdProfessor(professorId);
			return ResponseEntity.ok(new ApiResponse("Disponibilidades deletadas com sucesso", null));
		}

		return ResponseEntity.ok(new ApiResponse("Sem disponibilidades cadastradas", null));


	}
	

}
