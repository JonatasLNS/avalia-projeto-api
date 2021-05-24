package com.projetofinal.avaliaProjeto.model.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.projetofinal.avaliaProjeto.model.enums.TipoAvaliacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "dimensao", schema = "avalia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dimensao {
	
	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "subdescricao")
	private String subdescricao;
	
	@Column(name = "peso")
	private Long peso;
	
	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoAvaliacao tipo;
	
	@OneToMany(mappedBy = "dimensao", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy("id")
	private Set<SubDimensao> subdimensoes ;
}
