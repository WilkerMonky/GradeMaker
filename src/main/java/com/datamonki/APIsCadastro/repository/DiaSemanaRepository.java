package com.datamonki.APIsCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.DiaSemana;

// ultiliza o jpaRepository para implementar as operacoes de CRUD
@Repository
public interface DiaSemanaRepository  extends JpaRepository<DiaSemana, Integer>{

}
