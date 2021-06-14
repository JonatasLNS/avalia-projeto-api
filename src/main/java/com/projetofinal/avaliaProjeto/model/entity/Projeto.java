package com.projetofinal.avaliaProjeto.model.entity;

import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.projetofinal.avaliaProjeto.model.enums.StatusAvaliacao;
import com.projetofinal.avaliaProjeto.model.enums.StatusProjeto;

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
	
	@OneToOne
	@JoinColumn(name = "id_professor_orientador")
	private Professor professorOrientador;
	
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private StatusProjeto status;
	
	 @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL,  fetch=FetchType.EAGER)
	 @OrderBy("id")
	 private List<Avaliacao> avaliacoes;
	
	@Transient
	double  percentualDeAcordo;
	
	@Transient
	double  percentualNaoDeAcordo;
	
	@Transient
	double  percentualParcialDeAcordo;
	
}
