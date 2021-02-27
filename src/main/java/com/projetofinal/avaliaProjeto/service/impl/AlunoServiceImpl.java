package com.projetofinal.avaliaProjeto.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.model.entity.Aluno;
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.repository.AlunoRepository;
import com.projetofinal.avaliaProjeto.model.repository.ProfessorRepository;
import com.projetofinal.avaliaProjeto.service.AlunoService;
import com.projetofinal.avaliaProjeto.service.ProfessorService;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	private AlunoRepository repository;

	@Override
	public Aluno salvarAluno(Aluno professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Aluno> obterPorId(Long id) {
		return repository.findById(id);
	}





	

}
