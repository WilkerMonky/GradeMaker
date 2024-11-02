package com.datamonki.APIsCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamonki.APIsCadastro.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Optional<Professor> findByNome(String nome);
    List<Professor> findByNomeContainingIgnoreCase(String nome);
}
