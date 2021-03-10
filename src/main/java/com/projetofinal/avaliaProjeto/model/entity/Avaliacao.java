package com.projetofinal.avaliaProjeto.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.projetofinal.avaliaProjeto.model.enums.StatusAvaliacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "avaliacao", schema = "avalia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
	
	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nota")
	private Long nota;
	
	@Column(name = "data_inicio")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataInicio;
	
	@Column(name = "data_fim")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataFim;
	
	@ManyToOne
	@JoinColumn(name= "id_projeto")
	private Projeto projeto;
	
	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor ;
	
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private StatusAvaliacao status;

}
