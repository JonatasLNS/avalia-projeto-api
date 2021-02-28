package com.projetofinal.avaliaProjeto.api.resource;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.projetofinal.avaliaProjeto.api.dto.ProjetoDTO;
import com.projetofinal.avaliaProjeto.api.dto.UsuarioDTO;
import com.projetofinal.avaliaProjeto.exception.ErroAutenticacao;
import com.projetofinal.avaliaProjeto.exception.RegraNegocioException;
import com.projetofinal.avaliaProjeto.model.entity.Aluno;
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Projeto;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.service.AlunoService;
import com.projetofinal.avaliaProjeto.service.ProfessorService;
import com.projetofinal.avaliaProjeto.service.ProjetoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
public class ProjetoResource {

	private final ProjetoService  service;
	private final  ProfessorService  professorService;
	private final AlunoService alunoService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ProjetoDTO dto) {
		try {
			Projeto entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
										
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar( @PathVariable("id") Long id, @RequestBody ProjetoDTO dto) {
		return service.obterPorId(id).map( entity -> {
			try {
				Projeto projeto = converter(dto);
				projeto.setId(entity.getId());
				service.atualizar(projeto);
				return ResponseEntity.ok(projeto);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () -> 
			new  ResponseEntity("Projeto não encontrado na basa de dados.",HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map( entidade -> {
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet( () ->
			new  ResponseEntity("Projeto não encontrado na basa de dados.",HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping
	public ResponseEntity  buscar(
			@RequestParam(value ="ano", required = false) Integer ano,
			@RequestParam(value ="semestre", required = false) Integer semestre,
			@RequestParam(value ="tema", required = false) String tema,
			@RequestParam(value ="professorOrientador", required = false) Long idProfessorOrientador,
			@RequestParam(value ="aluno", required = false) Long idAluno) {
		
		//posso tbm utilizar:
		//@RequestParam java.util.Map<String, String> params
		//todos são obrigatórios
		
		Projeto projetoFiltro = new Projeto();
		projetoFiltro.setAno(ano);
		projetoFiltro.setSemestre(semestre);
		projetoFiltro.setTema(tema);
		
		if(idProfessorOrientador != null) {
			Optional<Professor> professor = professorService.obterPorId(idProfessorOrientador);

			if(!professor.isPresent()) {
				return ResponseEntity.badRequest().body("Não foi possivel realizar a consulta. Projeto não encontrado.");
			}else {
				projetoFiltro.setOrientador(professor.get());
			}
			
		}
		
		List<Projeto> projetos = service.buscar(projetoFiltro);
		return ResponseEntity.ok(projetos);
	}
	
	private Projeto converter(ProjetoDTO dto) {
	
			Projeto projeto = new Projeto();
			
			projeto.setId(dto.getId());
			projeto.setAno(dto.getAno());
			projeto.setSemestre(dto.getSemestre());
			projeto.setTema(dto.getTema());
			
			Professor orientador =  professorService
					.obterPorId(dto.getProfessorOrientador())
					.orElseThrow( () -> new RegraNegocioException("Professor Orienador não encontrado para o Id informado."));
			
			projeto.setOrientador(orientador);
			
			Aluno aluno = alunoService
					.obterPorId(dto.getAluno())
					.orElseThrow( () -> new RegraNegocioException("Aluno não encontrado para o Id informado."));
			
			projeto.setAluno(aluno);
			
			return projeto;
	}
	
}