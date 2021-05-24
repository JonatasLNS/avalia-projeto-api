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
@RequestMapping("/api/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoResource {
	
	private final AvaliacaoService  service;
	private final DadosAvaliacaoService  dadosAvaliacaoService;
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value = "professorId", required = false) Long professorId
			) {
		
		Avaliacao avaliacaoFiltro = new Avaliacao();
		
		if( professorId != null) {
			Professor professor = new Professor();
			professor.setId(professorId);
			avaliacaoFiltro.setProfessor(professor);
		}

		List<Avaliacao> avaliacoes = service.buscar(avaliacaoFiltro);
		return  ResponseEntity.ok(avaliacoes);
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity getById(@PathVariable long id) {
		Optional<Avaliacao> avaliacao = service.obterPorId(id);
		return ResponseEntity.ok(avaliacao.get());
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody AvaliacaoDTO dto) {
		
		Avaliacao avaliacaoToUpdate = service.obterPorId(dto.getId()).get();
		avaliacaoToUpdate.setStatus(StatusAvaliacao.valueOf(dto.getStatus()));
		
		try {
			Avaliacao avaliacaoAtualizada = service.saveAvaliacao(avaliacaoToUpdate);
				
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

}
