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
import com.projetofinal.avaliaProjeto.model.entity.Professor;
import com.projetofinal.avaliaProjeto.model.entity.Usuario;
import com.projetofinal.avaliaProjeto.service.AlunoService;
import com.projetofinal.avaliaProjeto.service.AvaliacaoService;
import com.projetofinal.avaliaProjeto.service.ProfessorService;
import com.projetofinal.avaliaProjeto.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/professores")
public class ProfessorResource {
	
	private final ProfessorService  service;
	
	public ProfessorResource(ProfessorService service) {
		this.service= service;
	}
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "disciplina", required = false) String disciplina
			) {
		
		Professor professorFiltro = new Professor();
		professorFiltro.setEspecialidade(disciplina);
		
		if( nome != null) {
			Usuario usuario = new Usuario();
			usuario.setNome(nome);			
			professorFiltro.setUsuario(usuario);
		}

		List<Professor> professores = service.buscar(professorFiltro);
		return  ResponseEntity.ok(professores);
	}

}
