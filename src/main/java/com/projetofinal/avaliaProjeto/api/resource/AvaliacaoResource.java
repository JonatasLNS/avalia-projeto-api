package com.projetofinal.avaliaProjeto.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
