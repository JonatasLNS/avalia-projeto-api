package com.projetofinal.avaliaProjeto.service;

import java.util.List;
import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;

public interface AvaliacaoService {
	
	public Optional<Avaliacao> obterPorId(Long id) ;

	public List<Avaliacao> obterPorProfessorId(Long id) ;
	
	public List<Avaliacao> salvarAvaliacoes(List<Avaliacao> avaliacoes);
	
	public Avaliacao saveAvaliacao(Avaliacao avaliacao);

	public List<Avaliacao> buscar(Avaliacao avaliacaoFiltro);

}
