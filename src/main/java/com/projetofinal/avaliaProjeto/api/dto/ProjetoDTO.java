package com.projetofinal.avaliaProjeto.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {
	
	private Long id;
	private Integer ano;
	private Integer semestre;
	private String tema;
	private Long alunoId; 
	private Long alunoNome; 
	private Long professorId; 
	private String professorNome; 
	private String situacao; 
	private Long nota; 
}
