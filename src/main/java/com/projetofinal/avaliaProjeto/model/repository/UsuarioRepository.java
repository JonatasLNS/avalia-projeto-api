package com.projetofinal.avaliaProjeto.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.avaliaProjeto.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findByNome(String nome);
	
	Optional<Usuario> findById(Long id);

}
