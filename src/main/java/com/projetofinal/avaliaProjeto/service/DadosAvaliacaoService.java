package com.projetofinal.avaliaProjeto.service;

import java.util.List;

import com.projetofinal.avaliaProjeto.model.entity.DadosAvaliacao;

public interface DadosAvaliacaoService {

	public DadosAvaliacao saveDadosAvaliacao(DadosAvaliacao dadosAvaliacao);
	
	public List<DadosAvaliacao> salvarDadosAvaliacoes(List<DadosAvaliacao> dadosAvaliacao);

	public List<DadosAvaliacao> buscar(DadosAvaliacao dadosFiltro);
	
	public List<DadosAvaliacao> obterPorAvaliacaoId(Long id);

}
