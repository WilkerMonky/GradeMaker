package com.datamonki.APIsCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamonki.APIsCadastro.model.Curso;
// ultiliza o jpaRepository para implementar as operacoes de CRUD
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    // busca um curso pelo nome
    Optional<Curso> findByNome(String nome);
    // busca um curso pelo nome ignorando o case(nao diferencia maiusculo de minusculo)
    List<Curso> findByNomeContainingIgnoreCase(String nome);
}
