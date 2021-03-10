package com.projetofinal.avaliaProjeto.service;

import java.util.List;
import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;

public interface AvaliacaoService {
	
	public List<Avaliacao> obterPorProfessorId(Long id) ;

}
