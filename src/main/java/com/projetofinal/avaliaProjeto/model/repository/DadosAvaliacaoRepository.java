package com.projetofinal.avaliaProjeto.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.avaliaProjeto.model.entity.DadosAvaliacao;

public interface DadosAvaliacaoRepository extends JpaRepository<DadosAvaliacao, Long> {

	List<DadosAvaliacao> findByAvaliacaoId(Long id);

}
