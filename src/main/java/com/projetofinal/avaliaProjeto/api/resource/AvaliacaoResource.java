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
import com.projetofinal.avaliaProjeto.model.entity.Projeto;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.model.enums.StatusAvaliacao;
import com.projetofinal.avaliaProjeto.model.enums.StatusProjeto;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;
import com.projetofinal.avaliaProjeto.service.DadosAvaliacaoService;
import com.projetofinal.avaliaProjeto.service.ProjetoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoResource {
	
	private static final int NUM_MAXIMO_AVALIADORES = 4;
	
	private final AvaliacaoService  service;
	private final ProjetoService  projetoService;
	private final DadosAvaliacaoService  dadosAvaliacaoService;
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value = "professorId", required = false) Long professorId,
			@RequestParam(value = "projetoId", required = false) Long projetoId
			) {
		
		Avaliacao avaliacaoFiltro = new Avaliacao();
		
		if( professorId != null) {
			Professor professor = new Professor();
			professor.setId(professorId);
			avaliacaoFiltro.setProfessor(professor);
		}
		
		if( projetoId != null) {
			Projeto projeto = new Projeto();
			projeto.setId(projetoId);
			avaliacaoFiltro.setProjeto(projeto);
		}

		List<Avaliacao> avaliacoes = service.buscar(avaliacaoFiltro);
		
		for (Avaliacao avaliacao : avaliacoes) {
			Projeto projeto = avaliacao.getProjeto();
			projeto.setAvaliacoes(null);
			avaliacao.setProjetoTransient(projeto);
		}
		return  ResponseEntity.ok(avaliacoes);
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity getById(@PathVariable long id) {
		Optional<Avaliacao> avaliacao = service.obterPorId(id);
		
		Projeto projeto = avaliacao.get().getProjeto();
		projeto.setAvaliacoes(null);
		avaliacao.get().setProjetoTransient(projeto);
		
		return ResponseEntity.ok(avaliacao.get());
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody AvaliacaoDTO dto) {
		
		Avaliacao avaliacaoToUpdate = service.obterPorId(dto.getId()).get();
		avaliacaoToUpdate.setStatus(StatusAvaliacao.valueOf(dto.getStatus()));
		
		try {
			Avaliacao avaliacaoAtualizada = service.saveAvaliacao(avaliacaoToUpdate);
			
			atualizaStatusProjeto(avaliacaoToUpdate);
	
			ArrayList<DadosAvaliacaoDTO> dadosAvaliacaoDTOList =  dto.getDadosAvaliacaoJson();
			ArrayList<DadosAvaliacao> dadosAvaliacaoList = new ArrayList<DadosAvaliacao>();
			
			for (DadosAvaliacaoDTO dadosAvaliacaoDTO : dadosAvaliacaoDTOList) {
				DadosAvaliacao dadosAvaliacao = new DadosAvaliacao();
				dadosAvaliacao.setAvaliacao(avaliacaoAtualizada);
				dadosAvaliacao.setValorSelect(dadosAvaliacaoDTO.getSelectValue());
				dadosAvaliacao.setObservacao(dadosAvaliacaoDTO.getTextObservacao());
				dadosAvaliacao.setChecked(dadosAvaliacaoDTO.getCheckedObservacao());
								
				Eixo eixo = new Eixo();
				eixo.setId(dadosAvaliacaoDTO.getIdEixo());
				dadosAvaliacao.setEixo(eixo);
				
				DadosAvaliacaoID dadosAvaliacaoID = new DadosAvaliacaoID();
				dadosAvaliacaoID.setAvaliacaoId(avaliacaoAtualizada.getId());
				dadosAvaliacaoID.setEixoId(eixo.getId());
				dadosAvaliacao.setDadosAvaliacaoID(dadosAvaliacaoID);
				
				dadosAvaliacaoList.add(dadosAvaliacao);
			}
			
			List<DadosAvaliacao> dadosAvaliacoesSalvos = dadosAvaliacaoService.salvarDadosAvaliacoes(dadosAvaliacaoList);

			return new ResponseEntity(avaliacaoAtualizada, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private void atualizaStatusProjeto(Avaliacao avaliacaoToUpdate) {
		Avaliacao avaliacaoFiltro = new Avaliacao();
		Projeto projetoToUpdate = avaliacaoToUpdate.getProjeto();
		avaliacaoFiltro.setProjeto(avaliacaoToUpdate.getProjeto());
		
		List<Avaliacao> avaliacoesProjeto = service.buscar(avaliacaoFiltro);
		
		int countAvaliacoesConcluidas = 0;
				
		for (Avaliacao avaliacao : avaliacoesProjeto) {
			if(avaliacao.getStatus() == StatusAvaliacao.EFETIVADO) {
				countAvaliacoesConcluidas++;
			}
		}
		
		if (countAvaliacoesConcluidas > 0  && countAvaliacoesConcluidas == NUM_MAXIMO_AVALIADORES ) {
			projetoToUpdate.setStatus(StatusProjeto.CONCLUIDO);
		} else if (countAvaliacoesConcluidas > 0  && countAvaliacoesConcluidas < NUM_MAXIMO_AVALIADORES ) {
			projetoToUpdate.setStatus(StatusProjeto.EM_ANDAMENTO);
		}
		
		projetoService.salvar(projetoToUpdate);
	}

}
