package com.projetofinal.avaliaProjeto.api.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.projetofinal.avaliaProjeto.api.dto.ProfessorDTO;
import com.projetofinal.avaliaProjeto.api.dto.ProjetoDTO;
import com.projetofinal.avaliaProjeto.exception.RegraNegocioException;
import com.projetofinal.avaliaProjeto.model.entity.Aluno;
import com.projetofinal.avaliaProjeto.model.entity.Avaliacao;
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Projeto;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.model.enums.StatusAvaliacao;
import com.projetofinal.avaliaProjeto.service.AlunoService;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;
import com.projetofinal.avaliaProjeto.service.ProfessorService;
import com.projetofinal.avaliaProjeto.service.ProjetoService;
import com.projetofinal.avaliaProjeto.service.UsuarioService;
import com.sun.xml.bind.v2.TODO;

import javassist.expr.NewArray;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
public class ProjetoResource {

	private final ProjetoService  service;
	private final  ProfessorService  professorService;
	private final AlunoService alunoService;
	private final AvaliacaoService avaliacaoService;
	private final UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ProjetoDTO dto) {
		try {
			Aluno aluno = alunoService.obterPorId(dto.getIdAluno()).get();
			
			String semestreString= dto.getSemestre();
	        String[] result = semestreString.split("\\.");
	        Integer ano = Integer.parseInt(result[0]);
	        Integer semestre = Integer.parseInt(result[1]);
	        
	        Optional<Professor> professorOrientador = professorService.obterPorUsuarioId(dto.getIdUsuarioOrientador());
			
			Projeto entidade = Projeto.builder()
															.aluno(aluno)
															.ano(ano)
															.semestre(semestre)
															.tema(dto.getTema())
															.professorOrientador(professorOrientador.get()).build();
			try {
				Projeto projetoSalvo = service.salvar(entidade);
				
				List<ProfessorDTO> listaProfessoresDTO = dto.getListaProfessores();
				List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
				
				//CADASTRA AVALIAÇÕES PENDENTES PARA CADA PROFESSOR DA BANCA
				for (ProfessorDTO professorDTO : listaProfessoresDTO) {
					Professor professor = professorService.obterPorId(professorDTO.getId()).get();
					
					Avaliacao avaliacao = Avaliacao.builder()
																.projeto(projetoSalvo)
																.professor(professor)
																.status(StatusAvaliacao.PENDENTE).build();
					
					avaliacoes.add(avaliacao);
				}
				
				//CADASTRA AVALIAÇÃO PENDENTE PARA O PROFESSOR ORIENTADOR
				Avaliacao avaliacao = Avaliacao.builder()
						.projeto(projetoSalvo)
						.professor(professorOrientador.get())
						.status(StatusAvaliacao.PENDENTE).build();

				avaliacoes.add(avaliacao);
				
				List<Avaliacao> avaliacoesSalvas = avaliacaoService.salvarAvaliacoes(avaliacoes);
				
				return new ResponseEntity(avaliacoesSalvas, HttpStatus.CREATED);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
										
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar( @PathVariable("id") Long id, @RequestBody ProjetoDTO dto) {
		return service.obterPorId(id).map( entity -> {
			try {
				/*
				 * Projeto projeto = converter(dto); projeto.setId(entity.getId());
				 * service.atualizar(projeto);
				 */
				return ResponseEntity.ok(new Projeto());
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
			@RequestParam(value ="id", required = false) Long id,
			@RequestParam(value ="ano", required = false) Integer ano,
			@RequestParam(value ="semestre", required = false) Integer semestre,
			@RequestParam(value ="tema", required = false) String tema,
			@RequestParam(value ="aluno", required = false) Long idAluno) {
		
		//posso tbm utilizar:
		//@RequestParam java.util.Map<String, String> params
		//todos são obrigatórios
		
		Projeto projetoFiltro = new Projeto();
		projetoFiltro.setId(id);
		projetoFiltro.setAno(ano);
		projetoFiltro.setSemestre(semestre);
		projetoFiltro.setTema(tema);
		
		List<Projeto> projetos = service.buscar(projetoFiltro);
		return ResponseEntity.ok(projetos);
	}
	
	/*private Projeto converter(ProjetoDTO dto) {
	
			Projeto projeto = new Projeto();
			
			projeto.setId(dto.getId());
			projeto.setAno(dto.getAno());
			projeto.setSemestre(dto.getSemestre());
			projeto.setTema(dto.getTema());
			
			Aluno aluno = alunoService
					.obterPorId(dto.getAlunoId())
					.orElseThrow( () -> new RegraNegocioException("Aluno não encontrado para o Id informado."));
			
			projeto.setAluno(aluno);
			
			return projeto;
	}*/
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity obterProjetoById(@PathVariable long id) {
		Optional<Projeto> avaliacoes = service.obterPorId(id);
		
		return ResponseEntity.ok(avaliacoes);
	}
	
}
