package com.projetofinal.avaliaProjeto.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	Optional<Professor> findByUsuario(Usuario usuario);
	
	Optional<Professor> findByUsuarioId(Long usuarioId);

}
