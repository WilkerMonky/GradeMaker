package com.datamonki.APIsCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer>{

}
