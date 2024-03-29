package com.projetofinal.avaliaProjeto.api.dto;

import java.util.ArrayList;
import java.util.List;

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
public class AvaliacaoDTO {
	
	private Long id;
	private String Status;
	private ArrayList<DadosAvaliacaoDTO> dadosAvaliacaoJson;
	
}
