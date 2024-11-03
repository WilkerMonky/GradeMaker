package com.datamonki.APIsCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.Disciplina;

// ultiliza o jpaRepository para implementar as operacoes de CRUD
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    // busca uma disciplina pelo nome
    Optional<Disciplina> findByNome(String nome);
    // busca uma disciplina pelo nome ignorando o case(nao diferencia maiusculo de minusculo)
    List<Disciplina> findByNomeContainingIgnoreCase(String nome);
}
