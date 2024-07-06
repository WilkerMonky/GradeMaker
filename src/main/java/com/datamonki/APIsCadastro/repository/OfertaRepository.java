package com.datamonki.APIsCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

}
