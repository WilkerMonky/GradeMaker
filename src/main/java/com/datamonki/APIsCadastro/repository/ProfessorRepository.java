package com.datamonki.APIsCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamonki.APIsCadastro.model.Professor;

// ultiliza o jpaRepository para implementar as operacoes de CRUD
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    // busca um professor pelo nome
    Optional<Professor> findByNome(String nome); 
    // busca um professor pelo nome ignorando o case(nao diferencia maiusculo de minusculo)
    List<Professor> findByNomeContainingIgnoreCase(String nome);
}
