package com.projetofinal.avaliaProjeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
import com.projetofinal.avaliaProjeto.model.entity.DadosAvaliacao;
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.repository.AvaliacaoRepository;
import com.projetofinal.avaliaProjeto.model.repository.DadosAvaliacaoRepository;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;
import com.projetofinal.avaliaProjeto.service.DadosAvaliacaoService;

@Service
public class DadosAvaliacaoServiceImpl implements DadosAvaliacaoService {
	
private DadosAvaliacaoRepository repository;
	
	@Autowired
	public DadosAvaliacaoServiceImpl(DadosAvaliacaoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public DadosAvaliacao saveDadosAvaliacao(DadosAvaliacao dadosAvaliacao) {
		return repository.save(dadosAvaliacao);
	}

	@Override
	public List<DadosAvaliacao> salvarDadosAvaliacoes(List<DadosAvaliacao> dadosAvaliacao) {
		return repository.saveAll(dadosAvaliacao);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DadosAvaliacao> buscar(DadosAvaliacao dadosFiltro) {
		Example example = Example.of( dadosFiltro, 
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}

	@Override
	public List<DadosAvaliacao> obterPorAvaliacaoId(Long id) {
		return repository.findByAvaliacaoId(id);
	}

}
