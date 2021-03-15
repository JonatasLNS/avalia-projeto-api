package com.projetofinal.avaliaProjeto.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
	
	private Long id;
	private String nome;
	private String curso;
	private Long matricula;
}
