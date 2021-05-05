package com.projetofinal.avaliaProjeto.service;

import java.util.List;
import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;

public interface ProfessorService {
	
	Professor salvarProfessor(Professor professor);
	
	Optional<Professor> obterPorId(Long id);
	
	Optional<Professor> obterPorUsuarioId(Long id);
	
	List<Professor> buscar (Professor professor);

	Optional<Professor> obterPorUsuario(Usuario usuario);

}
