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
@Table( name = "sub_dimensao", schema = "avalia")
@Data
@Builder
public class SubDimensao {
	
	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "peso")
	private Long peso;
	
	@ManyToOne
	@JoinColumn(name = "id_dimensao")
	private Dimensao dimensao ;
	

}
