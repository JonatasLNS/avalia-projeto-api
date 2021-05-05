package com.projetofinal.avaliaProjeto.service;

import java.util.Optional;

import com.projetofinal.avaliaProjeto.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	public Optional<Usuario> obterPorNome(String nome);
	
	public Optional<Usuario> obterPorId(Long id);

}
