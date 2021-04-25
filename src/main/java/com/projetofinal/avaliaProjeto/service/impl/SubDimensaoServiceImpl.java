package com.projetofinal.avaliaProjeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.model.entity.SubDimensao;
import com.projetofinal.avaliaProjeto.model.repository.SubDimensaoRepository;
import com.projetofinal.avaliaProjeto.service.SubDimensaoService;

@Service
public class SubDimensaoServiceImpl implements SubDimensaoService {
	
private SubDimensaoRepository repository;
	
	@Autowired
	public SubDimensaoServiceImpl(SubDimensaoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<SubDimensao> obterSubDimensoes() {
		return repository.findAll();
	}

}
