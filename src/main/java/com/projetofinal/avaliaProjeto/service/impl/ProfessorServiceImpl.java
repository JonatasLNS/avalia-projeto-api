package com.projetofinal.avaliaProjeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.model.repository.ProfessorRepository;
import com.projetofinal.avaliaProjeto.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	private ProfessorRepository repository;
	
	@Autowired
	public ProfessorServiceImpl(ProfessorRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Professor salvarProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Professor> obterPorId(Long id) {
		return repository.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Professor> buscar(Professor professor) {
		Example example = Example.of( professor, 
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}

	@Override
	public Optional<Professor> obterPorUsuarioId(Long id) {
		return repository.findByUsuarioId(id);
	}

	@Override
	public Optional<Professor> obterPorUsuario(Usuario usuario) {
		return null;
	}



	

}
