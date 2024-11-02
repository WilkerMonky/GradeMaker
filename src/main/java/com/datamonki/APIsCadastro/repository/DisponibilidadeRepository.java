package com.datamonki.APIsCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datamonki.APIsCadastro.model.Disponibilidade;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Integer> {

        @Query (value = "SELECT COUNT(*) FROM  disponibilidade WHERE professor_id=:professorId", nativeQuery = true)
        Integer verifyDisponibilidadeProfessor(@Param("professorId") Integer professorId);


        @Query (
                value = "SELECT CASE WHEN COUNT (*) > 0 THEN  true ELSE false END " +
                        " FROM disponibilidade"  +
                        " WHERE professor_id=:professorId AND dia_semana_id=:diaSemanaId AND turno_id=:turnoId " +
                        " AND semestre=:semestre AND ano=:ano", nativeQuery = true)
        Boolean verifyRepeticao(@Param("professorId")
                                Integer professorId, @Param("diaSemanaId") Integer diaSemanaId,
                                @Param("turnoId") Integer turnoID, @Param("semestre") Integer semestre, @Param("ano") Integer ano);

}
