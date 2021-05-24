package com.projetofinal.avaliaProjeto.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosAvaliacaoDTO {
	
	private Integer selectValue;
	private String textObservacao;
	private Boolean checkedObservacao;
	private Long idEixo;
	
}
