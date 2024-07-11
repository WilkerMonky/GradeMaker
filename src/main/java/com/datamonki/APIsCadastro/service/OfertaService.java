package com.datamonki.APIsCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.OfertaDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Oferta;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;
import com.datamonki.APIsCadastro.repository.MatrizRepository;
import com.datamonki.APIsCadastro.repository.OfertaRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class OfertaService {

	@Autowired
	private OfertaRepository ofertaRepository;
	@Autowired
	private MatrizRepository matrizRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;

	private void verificarId(Integer id) {
		if (!ofertaRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	private void verificar(OfertaDto ofertaDto) {
		if (ofertaDto.semestre() == null) {
			throw new ValidarException("Semestre esta vazio");
		}
		if (!matrizRepository.existsById(ofertaDto.matrizId())) {
			throw new ValidarException("Nenhuma matriz com id informado existe");
		}
		if (!disciplinaRepository.existsById(ofertaDto.disciplinaId())) {
			throw new ValidarException("Nenhuma disciplina com id informado existe");
		}
	}

	@Transactional
	public ResponseEntity<ApiResponse> save(OfertaDto ofertaDto) {
		verificar(ofertaDto);
		Oferta oferta = new Oferta();
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setMatriz(matrizRepository.findById(ofertaDto.matrizId()).get());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplinaId()).get());
		ofertaRepository.save(oferta);
		return ResponseEntity.ok(new ApiResponse("Oferta cadastrada com sucesso", oferta));
	}

	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		Oferta oferta =  ofertaRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Oferta localizada", oferta));
	}

	public ResponseEntity<ApiResponse>  getAll() {
		List<Oferta> ofertas = ofertaRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de ofertas", ofertas));
	}

	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, OfertaDto ofertaDto) {
		verificarId(id);
		verificar(ofertaDto);
		Oferta oferta = ofertaRepository.findById(id).get();
		oferta.setId(id);
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setMatriz(matrizRepository.findById(ofertaDto.matrizId()).get());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplinaId()).get());
		return ResponseEntity.ok(new ApiResponse("Oferta atualizada com sucesso", oferta));
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Oferta oferta = ofertaRepository.findById(id).get();
		ofertaRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Oferta deletada com sucesso", oferta));
	}
}
