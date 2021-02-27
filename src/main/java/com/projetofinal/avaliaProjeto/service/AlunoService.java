package com.projetofinal.avaliaProjeto.service;

import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Aluno;

public interface AlunoService {
	
	public Aluno salvarAluno(Aluno professor) ;

	public Optional<Aluno> obterPorId(Long id) ;

}
