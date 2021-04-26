package com.projetofinal.avaliaProjeto.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.avaliaProjeto.api.dto.AvaliacaoDTO;
import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoResource {
	
	private final AvaliacaoService  service;
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity listarByProfessorId(@PathVariable long id) {
		List<Avaliacao> avaliacoes = service.obterPorProfessorId(id);
		
		return ResponseEntity.ok(avaliacoes);
	}
	
	/*@PostMapping
	public ResponseEntity salvar(@RequestBody AvaliacaoDTO dto) {
		Avaliacao avaliacao = Avaliacao.builder()
															.nome(dto.getNome())
															 .email(dto.getEmail())
															 .senha(dto.getSenha()).build();
		
		try {
			Avaliacao avaliacaoSalva = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}*/

}
