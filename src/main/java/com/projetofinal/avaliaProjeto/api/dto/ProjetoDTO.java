package com.projetofinal.avaliaProjeto.api.dto;

import java.util.List;

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
	private String tema;
	private String curso;
	private List<ProfessorDTO> listaProfessores; 
	private Long idAluno; 
	private String nomeAluno; 
	private String semestre;
	private Long idUsuarioOrientador; 

}
