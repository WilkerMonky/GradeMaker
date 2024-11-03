package com.datamonki.APIsCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.APIsCadastro.dto.TipoCursoDto;
import com.datamonki.APIsCadastro.exception.IdNotFoundException;
import com.datamonki.APIsCadastro.exception.NameNotFoundException;
import com.datamonki.APIsCadastro.exception.ValidationException;
import com.datamonki.APIsCadastro.model.TipoCurso;
import com.datamonki.APIsCadastro.repository.TipoCursoRepository;
import com.datamonki.APIsCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class TipoCursoService {

	//Injeta o repositorio de tipo de curso
	@Autowired
	private TipoCursoRepository tipoCursoRepository;

	//Verifica se o id existe
	private void verificarId(Integer id) {
		if (!tipoCursoRepository.existsById(id)) {
			throw new IdNotFoundException("Não foi possivel encontrar com o Id " + id + ", verifique e tente novamente"); 
		}
	}

	//Verifica se o nome esta vazio ou se tem menos de 3 caracteres
	private void verificar(TipoCursoDto tipoCursoDto) {
		List<String> messages = new ArrayList<>();
		if (tipoCursoDto.nome().isEmpty()) {
			messages.add("Nome esta vazio");
		} else if (tipoCursoDto.nome().length() < 3) {
			messages.add("Nome deve estar acima de 3 caractere");
		}
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}	

	//Verifica se o nome existe
	private void verificarNome(String nome) {
		List<TipoCurso> tiposCurso = tipoCursoRepository.findByNomeContainingIgnoreCase(nome);
		if (tiposCurso.isEmpty()) {
			throw new NameNotFoundException("Não foi possível encontrar tipo de curso com o nome " + nome + ", verifique e tente novamente");
		}
	}

	//Salva o tipo de curso
	@Transactional
	public ResponseEntity<ApiResponse> save(TipoCursoDto tipoCursoDto) {
		verificar(tipoCursoDto);
		TipoCurso tipoCurso = new TipoCurso();
		tipoCurso.setNome(tipoCursoDto.nome());
		 tipoCursoRepository.save(tipoCurso);
		 
		 return ResponseEntity.ok(new ApiResponse("Tipo do curso cadastrado com sucesso", tipoCurso)); 
	}

	//Retorna o tipo de curso pelo id
	public ResponseEntity<ApiResponse> getById(Integer id) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		return ResponseEntity.ok(new ApiResponse("Tipo do curso localizado com sucesso", tipoCurso));
	}

	//Retorna todos os tipos de curso
	public ResponseEntity<ApiResponse> getAll() {
		List<TipoCurso> tiposCurso = tipoCursoRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Lista de tipos de curso cadastrados", tiposCurso));
	}

	//Retorna o tipo de curso pelo nome
	public ResponseEntity<ApiResponse> getByNome(String nome) {
		verificarNome(nome);
		TipoCurso tipoCurso = tipoCursoRepository.findByNomeContainingIgnoreCase(nome).get(0);
		return ResponseEntity.ok(new ApiResponse("Tipo do curso localizado com sucesso", tipoCurso));
	}

	//Atualiza o tipo de curso
	@Transactional
	public ResponseEntity<ApiResponse> update(Integer id, TipoCursoDto tipoCursoDto) {
		verificarId(id);
		verificar(tipoCursoDto);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCurso.setId(id);
		tipoCurso.setNome(tipoCursoDto.nome());
		tipoCursoRepository.save(tipoCurso);
		return ResponseEntity.ok(new ApiResponse("Tipo do curso alterado com sucesso", tipoCurso));
	}

	//Deleta o tipo de curso
	public ResponseEntity<ApiResponse> delete(Integer id) {
		verificarId(id);
		TipoCurso tipoCurso = tipoCursoRepository.findById(id).get();
		tipoCursoRepository.deleteById(id);
		return ResponseEntity.ok(new ApiResponse("Tipo do curso deletado com sucesso", tipoCurso));
	}
}
