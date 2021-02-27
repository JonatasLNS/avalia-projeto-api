package com.projetofinal.avaliaProjeto.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProjetoDTO {
	
	private Long id;
	private Integer ano;
	private Integer semestre;
	private String tema;
	private Long professorOrientador;
	private Long aluno; 
}
