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
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.repository.AvaliacaoRepository;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {
	
private AvaliacaoRepository repository;
	
	@Autowired
	public AvaliacaoServiceImpl(AvaliacaoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Optional<Avaliacao> obterPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Avaliacao> obterPorProfessorId(Long id) {
		return repository.findByProfessor_Id(id);
	}

	@Override
	public List<Avaliacao> salvarAvaliacoes(List<Avaliacao> avaliacoes) {
		return repository.saveAll(avaliacoes);
	}

	@Override
	public Avaliacao saveAvaliacao(Avaliacao avaliacao) {
		return repository.save(avaliacao);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Avaliacao> buscar(Avaliacao avaliacaoFiltro) {
		Example example = Example.of( avaliacaoFiltro, 
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}

}
