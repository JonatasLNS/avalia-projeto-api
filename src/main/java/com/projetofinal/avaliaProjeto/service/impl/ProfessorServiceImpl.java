package com.projetofinal.avaliaProjeto.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.repository.ProfessorRepository;
import com.projetofinal.avaliaProjeto.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	private ProfessorRepository repository;

	@Override
	public Professor salvarProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Professor> obterPorId(Long id) {
		return repository.findById(id);
	}



	

}
