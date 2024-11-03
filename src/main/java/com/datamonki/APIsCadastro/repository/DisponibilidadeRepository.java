package com.datamonki.APIsCadastro.repository;

import com.datamonki.APIsCadastro.dto.DisponibilidadeDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.Disponibilidade;

import java.util.List;

// ultiliza o jpaRepository para implementar as operacoes de CRUD
@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Integer> {

        @Query (value = "SELECT COUNT(*) FROM  disponibilidade WHERE professor_id=:professorId", nativeQuery = true)
        Integer getNumDisponibilidadeProfessor(@Param("professorId") Integer professorId);


        @Query (value = "SELECT  CASE WHEN COUNT(*) >0 THEN true ELSE false END " +
                "FROM  disponibilidade WHERE professor_id=:professorId", nativeQuery = true)
        Boolean verifyDisponibilidadeProfessor(@Param("professorId") Integer professorId);



    @Query (
                value = "SELECT CASE WHEN COUNT (*) > 0 THEN  true ELSE false END " +
                        " FROM disponibilidade"  +
                        " WHERE professor_id=:professorId AND dia_semana_id=:diaSemanaId AND turno_id=:turnoId " +
                        " AND semestre=:semestre AND ano=:ano", nativeQuery = true)
        Boolean verifyRepeticao(@Param("professorId")
                                Integer professorId, @Param("diaSemanaId") Integer diaSemanaId,
                                @Param("turnoId") Integer turnoID, @Param("semestre") Integer semestre, @Param("ano") Integer ano);

        @Query (value = "SELECT * FROM  disponibilidade WHERE professor_id=:professorId", nativeQuery = true)
        List<DisponibilidadeDto> findByIdProfessor(@Param("professorId") Integer professorId);

        @Modifying
        @Transactional
        @Query (value = "DELETE FROM  disponibilidade WHERE professor_id=:professorId", nativeQuery = true)
        void deleteByIdProfessor(@Param("professorId") Integer professorId);

    @Query(value = "SELECT d.professor_id, d.dia_semana_id, d.turno_id, d.semestre, d.ano " +
            "FROM disponibilidade d " +
            "WHERE d.professor_id = :professorId", nativeQuery = true)
    List<Object[]> findDisponibilidadeByProfessorId(@Param("professorId") Integer professorId);



}
