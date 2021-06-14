package com.projetofinal.avaliaProjeto.api.resource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.avaliaProjeto.api.dto.AvaliacaoDTO;
import com.projetofinal.avaliaProjeto.api.dto.DadosAvaliacaoDTO;
import com.projetofinal.avaliaProjeto.exception.RegraNegocioException;
import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
import com.projetofinal.avaliaProjeto.model.entity.DadosAvaliacao;
import com.projetofinal.avaliaProjeto.model.entity.DadosAvaliacaoID;
import com.projetofinal.avaliaProjeto.model.entity.Eixo;
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.model.enums.StatusAvaliacao;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;
import com.projetofinal.avaliaProjeto.service.DadosAvaliacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dadosAvaliacao")
@RequiredArgsConstructor
public class DadosAvaliacaoResource {
	
	private final DadosAvaliacaoService  service;
	private final AvaliacaoService  avaliacaoService;
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value = "avaliacaoId", required = false) Long avaliacaoId
			) {
		
		List<DadosAvaliacao> dados = service.obterPorAvaliacaoId(avaliacaoId);
		return  ResponseEntity.ok(dados);			
		
	}
	
}
