package com.projetofinal.avaliaProjeto.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EixoDTO {
	
	private Long id;
	private String descricao;
	private Long peso;

}
