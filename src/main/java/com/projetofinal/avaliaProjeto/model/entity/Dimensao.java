package com.projetofinal.avaliaProjeto.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.projetofinal.avaliaProjeto.model.enums.TipoAvaliacao;

import lombok.Builder;
import lombok.Data;


@Entity
@Table( name = "dimensao", schema = "avalia")
@Data
@Builder
public class Dimensao {
	
	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "peso")
	private Long peso;
	
	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoAvaliacao tipo;
}
