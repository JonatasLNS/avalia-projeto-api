package com.projetofinal.avaliaProjeto.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table( name = "projeto", schema = "avalia")
@Data
@Builder
public class Projeto {
	
	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ano")
	private Integer ano;
	
	@Column(name = "semestre")
	private Integer semestre;
	
	@Column(name = "tema")
	private String tema;
	
	@ManyToOne
	@JoinColumn(name = "id_prof_orientador")
	private Professor orientador;
	
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno; 
	
}
