package com.projetofinal.avaliaProjeto.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.avaliaProjeto.exception.ErroAutenticacao;
import com.projetofinal.avaliaProjeto.exception.RegraNegocioException;
import com.projetofinal.avaliaProjeto.model.entity.Aluno;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.model.repository.UsuarioRepository;
import com.projetofinal.avaliaProjeto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository repository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o e-mail informado.");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email)   {
		
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este e-mail.");
		}
		
	}

	@Override
	public Optional<Usuario> obterPorNome(String nome) {
		return repository.findByNome(nome);
	}

}
