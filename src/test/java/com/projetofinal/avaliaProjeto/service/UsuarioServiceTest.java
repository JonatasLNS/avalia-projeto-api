package com.projetofinal.avaliaProjeto.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.projetofinal.avaliaProjeto.exception.ErroAutenticacao;
import com.projetofinal.avaliaProjeto.exception.RegraNegocioException;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.model.repository.UsuarioRepository;
import com.projetofinal.avaliaProjeto.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@SpyBean
	UsuarioServiceImpl service;
	
	@MockBean
	UsuarioRepository repository;
	
	@Test
	public void deveSalvarUmUsuario() {
		org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
				//cenário
				Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
				Usuario usuario = Usuario.builder()
						.nome("nome")
						.email("email@email.com")
						.senha("senha").id(1l).build();
				
				Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
				
				//ação
				Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
				
				//verificação
				Assertions.assertThat(usuarioSalvo).isNotNull();
				Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
				Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
				Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@email.com");
				Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");
		});
	}
	
	@Test
	public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
		//garante que chamou a exceção
				org.junit.jupiter.api.Assertions.assertThrows(RegraNegocioException.class, () -> {
					//cenário
					String email = "email@email.com";
					Usuario usuario = Usuario.builder().email(email).build();
					Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);
							
					//acao
					service.salvarUsuario(usuario);
					
					//verificacao
					Mockito.verify(repository, Mockito.never()).save(usuario);
				});
	}
	
	@Test
	public void deveAutenticarUmUsuarioComSucesso() {
		//para garantir q não lancou exceção
		org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
			//cenário
			String email = "email@email.com";
			String senha = "senha";
			
			Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
			Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
			
			//acao
			Usuario result = service.autenticar(email, senha);
			
			//verificacao
			Assertions.assertThat(result).isNotNull();
		});
	}
	
	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuariocadastradoComOEmailInformado() {
		
			//cenário
			Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
			
			//acao
			Throwable exception = Assertions.catchThrowable(() -> service.autenticar("email@email.com", "senha"));
			Assertions.assertThat(exception)
									.isInstanceOf(ErroAutenticacao.class)
									.hasMessage("Usuário não encontrado para o e-mail informado.");
		
	}
	
	@Test
	public void deveLancarErroQuandoSenhaNaoBater() {
			//cenário
			String senha = "senha";
			Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).id(1l).build();
			Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
			
			//acao
			Throwable exception =  Assertions.catchThrowable( () -> service.autenticar("email@email.com", "123"));
			Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha inválida.");

	}
	
	@Test
	public void deveValidarEmail() {
		org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
				//cenário
				Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
				repository.deleteAll();
				
				//acao
				service.validarEmail("email@email.com");
		});
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		//garante que chamou a exceção
		org.junit.jupiter.api.Assertions.assertThrows(RegraNegocioException.class, () -> {
			//cenario
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
			
			//acao
			service.validarEmail("usuario@email.com");
			
		});
	}

}
