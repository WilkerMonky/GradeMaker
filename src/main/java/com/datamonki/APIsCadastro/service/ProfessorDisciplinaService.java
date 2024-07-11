package com.datamonki.APIsCadastro.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.DisciplinaDto;
import com.datamonki.APIsCadastro.dto.ProfessorDisciplinaDto;
import com.datamonki.APIsCadastro.dto.ProfessorDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
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
import java.util.List;

@Service
public class ProfessorDisciplinaService {

	@Autowired
	private ProfessorDisciplinaRepository professorDisciplinaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
    public void verificarIds(ProfessorDisciplinaId professorDisciplinaId) {
        Optional<Professor> professor = professorRepository.findById(professorDisciplinaId.getProfessorId());
        Optional<Disciplina> disciplina = disciplinaRepository.findById(professorDisciplinaId.getDisciplinaId());

        if (professor.isEmpty()) {
            throw new IdNaoEncontradoException("Professor com ID " + professorDisciplinaId.getProfessorId() + " não encontrado");
        }
        if (disciplina.isEmpty()) {
            throw new IdNaoEncontradoException("Disciplina com ID " + professorDisciplinaId.getDisciplinaId() + " não encontrada");
        }
    }
	@Transactional
	public ResponseEntity<ApiResponse> save( @Valid ProfessorDisciplinaId professorDisciplinaId){
		
		verificarIds(professorDisciplinaId);
		Optional<Professor> professor  = professorRepository.findById(professorDisciplinaId.getProfessorId()); 
		Optional<Disciplina> disciplina = disciplinaRepository.findById(professorDisciplinaId.getDisciplinaId());
		
		
		ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(professorDisciplinaId);
		professorDisciplina.setProfessor(professor.get());
		professorDisciplina.setDisciplina(disciplina.get());
		professorDisciplinaRepository.save(professorDisciplina);
		

		ProfessorDto professorDto = new ProfessorDto(professor.get().getId(), professor.get().getNome());
		DisciplinaDto disciplinaDto = new DisciplinaDto(disciplina.get().getId(), disciplina.get().getNome());
		ProfessorDisciplinaDto professorDisciplinaDto= new ProfessorDisciplinaDto(professorDto, disciplinaDto);
		return ResponseEntity.ok(new ApiResponse("relacionamento salvo com sucesso ", professorDisciplinaDto));
		
	}
	
	public ResponseEntity<ApiResponse> getAll(){
		List<ProfessorDisciplina> professorDisciplinas = professorDisciplinaRepository.findAll();
		List<ProfessorDisciplinaDto> professorDisciplinaDtos = professorDisciplinas.stream().map(this::convertToDto).collect(Collectors.toList());
		
		return ResponseEntity.ok(new ApiResponse("Professores indexados com disciplinas", professorDisciplinaDtos));
	}
	
	
	public ResponseEntity<ApiResponse> delete(ProfessorDisciplinaId professorDisciplinaId){
		verificarIds(professorDisciplinaId);
		Optional<ProfessorDisciplina> professorDisciplina = professorDisciplinaRepository.findById(professorDisciplinaId);
		
		if(professorDisciplina.isPresent()) {
			ProfessorDisciplinaDto professorDisciplinaDto = convertToDto(professorDisciplina.get());
			professorDisciplinaRepository.delete(professorDisciplina.get());
			return ResponseEntity.ok(new ApiResponse("Deletado com sucesso", professorDisciplinaDto));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Relacionamento não localizado", null));
		}	
	}
	
	
	
	public ProfessorDisciplinaDto convertToDto(ProfessorDisciplina professorDisciplina) {
		Professor professor = professorDisciplina.getProfessor();
		Disciplina disciplina = professorDisciplina.getDisciplina();
		ProfessorDto professorDto = new ProfessorDto(professor.getId(), professor.getNome());
        DisciplinaDto disciplinaDto = new DisciplinaDto(disciplina.getId(), disciplina.getNome());
        return new ProfessorDisciplinaDto(professorDto, disciplinaDto);
	}
	
	
	
	
	
	/*
	 * 
	 * 
	 * */
	
}
