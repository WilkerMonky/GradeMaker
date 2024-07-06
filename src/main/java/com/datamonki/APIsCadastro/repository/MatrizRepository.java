package com.datamonki.APIsCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.Matriz;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Integer>{

}
