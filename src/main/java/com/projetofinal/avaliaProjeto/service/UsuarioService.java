package com.projetofinal.avaliaProjeto.service;

import com.projetofinal.avaliaProjeto.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);

}
