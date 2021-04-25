package com.projetofinal.avaliaProjeto.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.avaliaProjeto.model.entity.Dimensao;
import com.projetofinal.avaliaProjeto.model.entity.SubDimensao;

public interface SubDimensaoRepository extends JpaRepository<SubDimensao, Long>{
	
	List<SubDimensao> findAll();

}
