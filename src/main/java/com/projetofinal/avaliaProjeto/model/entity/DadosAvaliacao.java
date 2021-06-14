package com.projetofinal.avaliaProjeto.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "dados_avaliacao", schema = "avalia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosAvaliacao {
	
	@EmbeddedId
	private DadosAvaliacaoID dadosAvaliacaoID;
	
	@Column(name = "valor_select")
	private Integer valorSelect;
	
	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "is_checked")
	private boolean isChecked;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "id_eixo", nullable = false, insertable = false, updatable = false)
	private Eixo eixo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_avaliacao", nullable = false, insertable = false, updatable = false)
	private Avaliacao avaliacao ;
	
	
}
