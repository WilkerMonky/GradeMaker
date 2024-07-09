package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
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

	public void verificarId(Integer id) {
		if (!ofertaRepository.existsById(id)) {
			throw new IdNaoEncontradoException();
		}
	}

	public void verificar(Oferta oferta, OfertaDto ofertaDto) {
		List<String> erros = new LinkedList<>();
		if (oferta.getSemestre().toString().isBlank()) {
			erros.add("Semestre esta vazio");
		}
		if (!matrizRepository.existsById(ofertaDto.matrizId())) {
			erros.add("Nenhuma matriz com id informado existe");
		}
		if (!disciplinaRepository.existsById(ofertaDto.disciplinaId())) {
			erros.add("Nenhuma disciplina com id informado existe");
		}
		if (!erros.isEmpty()) {
			throw new ValidarException(erros);
		}
	}

	@Transactional
	public ResponseEntity<ApiResponse> save(OfertaDto ofertaDto) {
		Oferta oferta = new Oferta();
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setMatriz(matrizRepository.findById(ofertaDto.matrizId()).get());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplinaId()).get());
		verificar(oferta, ofertaDto);
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
		Oferta oferta = ofertaRepository.findById(id).get();
		oferta.setId(id);
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setMatriz(matrizRepository.findById(ofertaDto.matrizId()).get());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplinaId()).get());
		verificar(oferta, ofertaDto);
		return ResponseEntity.ok(new ApiResponse("Oferta atualizada com sucesso", oferta));
	}

	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		Oferta oferta = ofertaRepository.findById(id).get();
		ofertaRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Oferta deletada com sucesso", oferta));
	}
}
