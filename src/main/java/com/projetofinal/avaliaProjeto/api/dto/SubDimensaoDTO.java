package com.projetofinal.avaliaProjeto.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubDimensaoDTO {
	
	private Long id;
	private Integer descricao;
	private Integer peso;

}
