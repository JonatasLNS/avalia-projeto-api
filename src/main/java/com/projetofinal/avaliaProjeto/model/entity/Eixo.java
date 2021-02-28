package com.projetofinal.avaliaProjeto.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "eixo", schema = "avalia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Eixo {
	
	@Id
	@Column(name = "id")	
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "peso")
	private Long peso;
	
	@ManyToOne
	@JoinColumn(name = "id_sub_dimensao")
	private SubDimensao subDimensao ;
	
	@Column(name = "flag_atende")
	private Boolean flagAtende;

}
