package com.projetofinal.avaliaProjeto.service;

import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Aluno;
import com.projetofinal.avaliaProjeto.model.entity.Projeto;

public interface AlunoService {
	
	public Aluno salvarAluno(Aluno professor) ;

	public Optional<Aluno> obterPorId(Long id) ;
	
	public Optional<Aluno> obterPorMatricula(Long matricula);

}
