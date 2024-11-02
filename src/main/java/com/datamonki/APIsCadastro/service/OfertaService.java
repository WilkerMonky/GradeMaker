package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.OfertaDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Oferta;
import com.datamonki.APIsCadastro.repository.CursoRepository;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;
import com.datamonki.APIsCadastro.repository.OfertaRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class OfertaService {

	@Autowired
	private OfertaRepository ofertaRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private CursoRepository cursoRepository;

	private void verificarId(Integer id) {
		if (!ofertaRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	private void verificar(OfertaDto ofertaDto) {
		if (ofertaDto.semestre() == null) {
			throw new ValidarException("Semestre está vazio");
		}
		if (!disciplinaRepository.existsById(ofertaDto.disciplinaId())) {
			throw new ValidarException("Nenhuma disciplina com id informado existe");
		}
		if (!cursoRepository.existsById(ofertaDto.cursoId())) {
			throw new ValidarException("Nenhuma curso com id informado existe");
		}
	}

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

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Oferta oferta =  ofertaRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Oferta localizada com sucesso", oferta)); 
	}

	public ResponseEntity<ApiResponse>  getAll() { 
		List<Oferta> ofertas = ofertaRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de ofertas cadastradas", ofertas)); 
	}

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

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Oferta oferta = ofertaRepository.findById(id).get();
		ofertaRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Oferta deletada com sucesso", oferta));
	}
}
