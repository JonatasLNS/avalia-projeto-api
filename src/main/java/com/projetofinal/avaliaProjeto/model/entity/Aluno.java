package com.projetofinal.avaliaProjeto.model.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "aluno", schema = "avalia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "curso")
	private String curso;
	
}
