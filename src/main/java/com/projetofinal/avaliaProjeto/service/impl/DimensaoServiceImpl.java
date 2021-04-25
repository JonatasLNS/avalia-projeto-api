package com.projetofinal.avaliaProjeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.model.entity.Dimensao;
import com.projetofinal.avaliaProjeto.model.repository.DimensaoRepository;
import com.projetofinal.avaliaProjeto.service.DimensaoService;

@Service
public class DimensaoServiceImpl implements DimensaoService {
	
private DimensaoRepository repository;
	
	@Autowired
	public DimensaoServiceImpl(DimensaoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Dimensao> obterDimensoes() {
		return repository.findByOrderByIdAsc();
	}

}
