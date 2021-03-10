package com.projetofinal.avaliaProjeto.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "projeto", schema = "avalia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	@JoinColumn(name = "id_aluno")
	private Aluno aluno; 
	
}
