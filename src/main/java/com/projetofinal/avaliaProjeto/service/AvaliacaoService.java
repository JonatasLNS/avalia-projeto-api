package com.projetofinal.avaliaProjeto.service;

import java.util.List;
import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;

public interface AvaliacaoService {
	
	public List<Avaliacao> obterPorProfessorId(Long id) ;
	
	public List<Avaliacao> salvarAvaliacoes(List<Avaliacao> avaliacoes);

}
