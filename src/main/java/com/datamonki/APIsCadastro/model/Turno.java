package com.datamonki.APIsCadastro.model;

import java.time.LocalTime;

import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@Table(name = "turno")
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
    @Column(name = "hora_inicio", columnDefinition = "time without time zone")
    
	private LocalTime horaInicio;
    
    @Column(name = "hora_fim", columnDefinition = "time without time zone")
	private LocalTime horaFim;

	public static Turno retornar_turno(@NotNull Integer dia_semana_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
