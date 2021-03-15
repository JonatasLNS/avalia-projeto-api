package com.projetofinal.avaliaProjeto.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.avaliaProjeto.model.entity.Aluno;
import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
import com.projetofinal.avaliaProjeto.service.AlunoService;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoResource {
	
	private final AlunoService  service;
	
	@GetMapping(path = {"/{matricula}"})
	public ResponseEntity getByMatricula(@PathVariable long matricula) {
		Optional<Aluno> aluno = service.obterPorMatricula(matricula);
		return ResponseEntity.ok(aluno);
	}

}
