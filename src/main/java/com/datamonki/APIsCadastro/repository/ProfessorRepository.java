package com.datamonki.APIsCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamonki.APIsCadastro.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
