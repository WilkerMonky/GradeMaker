package com.datamonki.APIsCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.OfertaDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.model.Oferta;
import com.datamonki.APIsCadastro.repository.CursoRepository;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;
import com.datamonki.APIsCadastro.repository.OfertaRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class OfertaService {

	//Injeta o repositorio de oferta
	@Autowired
	private OfertaRepository ofertaRepository;

	//Injeta o repositorio de disciplina
	@Autowired
	private DisciplinaRepository disciplinaRepository;

	//Injeta o repositorio de curso
	@Autowired
	private CursoRepository cursoRepository;

	//Verifica se o id existe
	private void verificarId(Integer id) {
		if (!ofertaRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel encontrar com o Id " + id + ", verifique e tente novamente"); 
		}
	}

	//Verifica se o semestre é valido
	private void verificar(OfertaDto ofertaDto) {
		List<String> messages = new ArrayList<>();
		if (ofertaDto.semestre() < 1 || ofertaDto.semestre() > 2) {
			messages.add("Semestre invalido");
		}
		if (!disciplinaRepository.existsById(ofertaDto.disciplinaId())) {
			messages.add("Nenhuma disciplina com id informado existe");
		}
		if (!cursoRepository.existsById(ofertaDto.cursoId())) {
			messages.add("Nenhum curso com id informado existe");
		}
		
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}

	//Salva a oferta
	@Transactional
	public ResponseEntity<ApiResponse> save(OfertaDto ofertaDto) { 
		verificar(ofertaDto); 
		Oferta oferta = new Oferta();
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplinaId()).get()); 
		oferta.setCurso(cursoRepository.findById(ofertaDto.cursoId()).get()); 
		ofertaRepository.save(oferta);
		return ResponseEntity.ok(new ApiResponse("Oferta cadastrada com sucesso", oferta));
	}

	//Retorna a oferta pelo id
	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Oferta oferta =  ofertaRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Oferta localizada com sucesso", oferta)); 
	}

	//Retorna todas as ofertas
	public ResponseEntity<ApiResponse>  getAll() { 
		List<Oferta> ofertas = ofertaRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de ofertas cadastradas", ofertas)); 
	}

	//Atualiza a oferta
	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, OfertaDto ofertaDto) {
		verificarId(id);
		verificar(ofertaDto);
		Oferta oferta = ofertaRepository.findById(id).get();
		oferta.setId(id);
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplinaId()).get());	
		ofertaRepository.save(oferta);
		return ResponseEntity.ok(new ApiResponse("Oferta atualizada com sucesso", oferta));
	}

	//Deleta a oferta
	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Oferta oferta = ofertaRepository.findById(id).get();
		ofertaRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Oferta deletada com sucesso", oferta));
	}
}
