package com.projetofinal.avaliaProjeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.model.entity.Dimensao;
import com.projetofinal.avaliaProjeto.model.entity.Eixo;
import com.projetofinal.avaliaProjeto.model.repository.DimensaoRepository;
import com.projetofinal.avaliaProjeto.model.repository.EixoRepository;
import com.projetofinal.avaliaProjeto.service.DimensaoService;
import com.projetofinal.avaliaProjeto.service.EixoService;

@Service
public class EixosServiceImpl implements EixoService {
	
private EixoRepository repository;
	
	@Autowired
	public EixosServiceImpl(EixoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Eixo> obterEixos() {
		return repository.findAll();
	}

}
