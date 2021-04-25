package com.projetofinal.avaliaProjeto.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.avaliaProjeto.model.entity.Eixo;

public interface EixoRepository extends JpaRepository<Eixo, Long> {
	
	List<Eixo> findAll();

}
