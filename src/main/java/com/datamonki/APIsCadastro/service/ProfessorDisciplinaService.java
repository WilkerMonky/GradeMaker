package com.datamonki.APIsCadastro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisciplinaDto;
import com.datamonki.APIsCadastro.dto.ProfessorDisciplinaDto;
import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.model.Disciplina;
import com.datamonki.APIsCadastro.model.Professor;
import com.datamonki.APIsCadastro.model.ProfessorDisciplina;
import com.datamonki.APIsCadastro.model.ProfessorDisciplinaId;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;
import com.datamonki.APIsCadastro.repository.ProfessorDisciplinaRepository;
import com.datamonki.APIsCadastro.repository.ProfessorRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ProfessorDisciplinaService {

	//Injeta o repositorio de professor disciplina
	@Autowired
	private ProfessorDisciplinaRepository professorDisciplinaRepository;

	//Injeta o repositorio de professor
	@Autowired
	private ProfessorRepository professorRepository;

	//Injeta o repositorio de disciplina
	@Autowired
	private DisciplinaRepository disciplinaRepository;

	//Verifica se o id do professor e da disciplina existe
	public void verificarIds(ProfessorDisciplinaId professorDisciplinaId) {
		Optional<Professor> professor = professorRepository.findById(professorDisciplinaId.getProfessorId());
		Optional<Disciplina> disciplina = disciplinaRepository.findById(professorDisciplinaId.getDisciplinaId());

		if (professor.isEmpty()) {
			throw new IdNotFoundException( 
					"Professor com ID " + professorDisciplinaId.getProfessorId() + " não encontrado");
		}
		if (disciplina.isEmpty()) {
			throw new IdNotFoundException(
					"Disciplina com ID " + professorDisciplinaId.getDisciplinaId() + " não encontrada");
		}
	}

	//Salva o relacionamento entre professor e disciplina
	@Transactional
	public ResponseEntity<ApiResponse> save(@Valid ProfessorDisciplinaId professorDisciplinaId) {

		verificarIds(professorDisciplinaId);
		Optional<Professor> professor = professorRepository.findById(professorDisciplinaId.getProfessorId());
		Optional<Disciplina> disciplina = disciplinaRepository.findById(professorDisciplinaId.getDisciplinaId());

		ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(professorDisciplinaId);
		professorDisciplina.setProfessor(professor.get());
		professorDisciplina.setDisciplina(disciplina.get());
		professorDisciplinaRepository.save(professorDisciplina);

		ProfessorDto professorDto = new ProfessorDto(professor.get().getNome());
		DisciplinaDto disciplinaDto = new DisciplinaDto(disciplina.get().getNome());
		ProfessorDisciplinaDto professorDisciplinaDto = new ProfessorDisciplinaDto(professorDto, disciplinaDto);
		return ResponseEntity.ok(new ApiResponse("Relacionamento salvo", professorDisciplinaDto));

	}

	//Retorna todos os relacionamentos entre professor e disciplina
	public ResponseEntity<ApiResponse> getAll() {
		List<ProfessorDisciplina> professorDisciplinas = professorDisciplinaRepository.findAll();
		List<ProfessorDisciplinaDto> professorDisciplinaDtos = professorDisciplinas.stream().map(this::convertToDto)
				.collect(Collectors.toList());

		return ResponseEntity.ok(new ApiResponse("Professores indexados com disciplinas", professorDisciplinaDtos));
	}

	//Deleta o relacionamento entre professor e disciplina
	public ResponseEntity<ApiResponse> delete(ProfessorDisciplinaId professorDisciplinaId) {
		verificarIds(professorDisciplinaId);
		Optional<ProfessorDisciplina> professorDisciplina = professorDisciplinaRepository
				.findById(professorDisciplinaId);

		if (professorDisciplina.isPresent()) {
			ProfessorDisciplinaDto professorDisciplinaDto = convertToDto(professorDisciplina.get());
			professorDisciplinaRepository.delete(professorDisciplina.get());
			return ResponseEntity.ok(new ApiResponse("Deletado com sucesso", professorDisciplinaDto));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse("Relacionamento não localizado", null));
		}
	}

	//Converte o relacionamento entre professor e disciplina para dto
	public ProfessorDisciplinaDto convertToDto(ProfessorDisciplina professorDisciplina) {
		Professor professor = professorDisciplina.getProfessor();
		Disciplina disciplina = professorDisciplina.getDisciplina();
		ProfessorDto professorDto = new ProfessorDto(professor.getNome());
		DisciplinaDto disciplinaDto = new DisciplinaDto(disciplina.getNome());
		return new ProfessorDisciplinaDto(professorDto, disciplinaDto);
	}

}
