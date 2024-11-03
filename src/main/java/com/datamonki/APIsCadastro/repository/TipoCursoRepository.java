package com.datamonki.APIsCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamonki.APIsCadastro.model.TipoCurso;

// ultiliza o jpaRepository para implementar as operacoes de CRUD
public interface TipoCursoRepository extends JpaRepository<TipoCurso, Integer> {
    // busca um tipo de curso pelo nome
    Optional<TipoCurso> findByNome(String nome);
    // busca um tipo de curso pelo nome ignorando o case(nao diferencia maiusculo de minusculo)
    List<TipoCurso> findByNomeContainingIgnoreCase(String nome);
}
