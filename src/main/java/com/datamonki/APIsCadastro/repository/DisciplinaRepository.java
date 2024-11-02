package com.datamonki.APIsCadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    Optional<Disciplina> findByNome(String nome);
    List<Disciplina> findByNomeContainingIgnoreCase(String nome);
}
