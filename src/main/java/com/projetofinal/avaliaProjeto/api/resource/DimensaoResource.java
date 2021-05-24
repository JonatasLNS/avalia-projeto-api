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
import com.projetofinal.avaliaProjeto.model.entity.Dimensao;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;
import com.projetofinal.avaliaProjeto.service.DimensaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dimensoes")
@RequiredArgsConstructor
public class DimensaoResource {
	
	private final DimensaoService  service;
	
	@GetMapping(path = {"/"})
	public ResponseEntity listarDimensoes() {
		List<Dimensao> avaliacoes = service.obterDimensoes();
		
		return ResponseEntity.ok(avaliacoes);
	}

}
