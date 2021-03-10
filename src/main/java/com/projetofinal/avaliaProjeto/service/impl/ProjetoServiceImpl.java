package com.projetofinal.avaliaProjeto.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetofinal.avaliaProjeto.exception.RegraNegocioException;
import com.projetofinal.avaliaProjeto.model.entity.Projeto;
import com.projetofinal.avaliaProjeto.model.repository.ProjetoRepository;
import com.projetofinal.avaliaProjeto.model.repository.UsuarioRepository;
import com.projetofinal.avaliaProjeto.service.ProjetoService;

@Service
public class ProjetoServiceImpl implements ProjetoService {
	
	private ProjetoRepository repository;
	
	@Autowired
	public ProjetoServiceImpl(ProjetoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Projeto salvar(Projeto projeto) {
		this.validar(projeto);
		return repository.save(projeto);
	}

	@Override
	@Transactional
	public Projeto atualizar(Projeto projeto) {
		Objects.requireNonNull(projeto.getId());
		this.validar(projeto);
		return repository.save(projeto);
	}

	@Override
	@Transactional
	public void deletar(Projeto projeto) {
		Objects.requireNonNull(projeto.getId());
		repository.delete(projeto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Projeto> buscar(Projeto projetoFiltro) {
		
		Example example = Example.of(projetoFiltro, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example);
	}
	
	public void validar(Projeto projeto) {
		
		if(projeto.getAno() == null || projeto.getAno().toString().length() != 4) {
			throw new RegraNegocioException("Informe um Mês válido.");
		}
		
		//TODO implementar outras validações
		if(projeto.getSemestre() == null ) {
			throw new RegraNegocioException("Informe um Semestre válido.");
		}
		
		if(projeto.getTema() == null || projeto.getTema().trim().equals("")) {
			throw new RegraNegocioException("Informe um Tema válido.");
		}
		
		if(projeto.getAluno() == null || projeto.getAluno().getId() == null) {
			throw new RegraNegocioException("Informe um Aluno.");
		}
		
	}

	@Override
	public Optional<Projeto> obterPorId(Long id) {
		return repository.findById(id);
	}

}
