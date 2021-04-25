package com.projetofinal.avaliaProjeto.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.model.entity.Aluno;
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Projeto;
import com.projetofinal.avaliaProjeto.model.repository.AlunoRepository;
import com.projetofinal.avaliaProjeto.model.repository.ProfessorRepository;
import com.projetofinal.avaliaProjeto.service.AlunoService;
import com.projetofinal.avaliaProjeto.service.ProfessorService;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	private AlunoRepository repository;
	
	@Autowired
	public AlunoServiceImpl(AlunoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Aluno salvarAluno(Aluno professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Optional<Aluno> obterPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Aluno> obterPorMatricula(Long matricula) {
		return repository.findByMatricula(matricula);
	}





	

}
