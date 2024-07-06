package com.datamonki.APIsCadastro.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.OfertaDto;
import com.datamonki.APIsCadastro.exception.IdNaoEncontradoException;
import com.datamonki.APIsCadastro.exception.ValidarException;
import com.datamonki.APIsCadastro.model.Oferta;
import com.datamonki.APIsCadastro.repository.DisciplinaRepository;
import com.datamonki.APIsCadastro.repository.MatrizRepository;
import com.datamonki.APIsCadastro.repository.OfertaRepository;

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
		if (!matrizRepository.existsById(ofertaDto.matriz_id())) {
			erros.add("Nenhuma matriz com id informado existe");
		}
		if (!disciplinaRepository.existsById(ofertaDto.disciplina_id())) {
			erros.add("Nenhuma disciplina com id informado existe");
		}
		if (!erros.isEmpty()) {
			throw new ValidarException(erros);
		}
	}

	@Transactional
	public Oferta save(OfertaDto ofertaDto) {
		Oferta oferta = new Oferta();
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setMatriz(matrizRepository.findById(ofertaDto.matriz_id()).get());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplina_id()).get());
		verificar(oferta, ofertaDto);
		return ofertaRepository.save(oferta);

	}

	public Oferta getById(Integer id) {
		verificarId(id);
		return ofertaRepository.findById(id).get();
	}

	public List<Oferta> getAll() {
		return ofertaRepository.findAll();
	}

	@Transactional
	public Oferta update(Integer id, OfertaDto ofertaDto) {
		verificarId(id);
		Oferta oferta = ofertaRepository.findById(id).get();
		oferta.setId(id);
		oferta.setSemestre(ofertaDto.semestre());
		oferta.setMatriz(matrizRepository.findById(ofertaDto.matriz_id()).get());
		oferta.setDisciplina(disciplinaRepository.findById(ofertaDto.disciplina_id()).get());
		verificar(oferta, ofertaDto);
		return ofertaRepository.save(oferta);
	}

	public Oferta delete(Integer id) {
		verificarId(id);
		Oferta tipoCurso = ofertaRepository.findById(id).get();
		ofertaRepository.deleteById(id);
		return tipoCurso;
	}
}
