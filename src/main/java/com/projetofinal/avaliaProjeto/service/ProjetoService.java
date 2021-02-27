package com.projetofinal.avaliaProjeto.service;

import java.util.List;
import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Projeto;

public interface ProjetoService {
	
		Projeto salvar(Projeto projeto);
		
		Projeto atualizar(Projeto projeto);
		
		void deletar(Projeto projeto);
		
		List<Projeto> buscar (Projeto projetoFiltro);
		
		Optional<Projeto> obterPorId(Long id);
}
