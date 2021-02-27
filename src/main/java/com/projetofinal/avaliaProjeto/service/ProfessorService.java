package com.projetofinal.avaliaProjeto.service;

import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Professor;

public interface ProfessorService {
	
	Professor salvarProfessor(Professor professor);
	
	Optional<Professor> obterPorId(Long id);

}
