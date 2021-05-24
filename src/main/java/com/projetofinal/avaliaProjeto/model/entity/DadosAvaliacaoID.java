package com.projetofinal.avaliaProjeto.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DadosAvaliacaoID implements Serializable {

	 @Column(name = "id_eixo")
	private Long eixoId;
	 
	 @Column(name = "id_avaliacao")
	private Long avaliacaoId;
	
	
}
