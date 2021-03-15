package com.projetofinal.avaliaProjeto.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.avaliaProjeto.model.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Optional<Aluno> findByMatricula(Long matricula);

}
