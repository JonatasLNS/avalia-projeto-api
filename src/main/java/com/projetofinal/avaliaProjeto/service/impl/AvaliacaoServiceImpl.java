package com.projetofinal.avaliaProjeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
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
	public List<Avaliacao> obterPorProfessorId(Long id) {
		return repository.findByProfessor_Id(id);
	}

}
