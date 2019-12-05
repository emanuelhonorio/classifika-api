package com.emanuelhonorio.resource;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.model.Anuncio;
import com.emanuelhonorio.model.Contato;
import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.repository.AnuncioRepository;
import com.emanuelhonorio.repository.ContatoRepository;
import com.emanuelhonorio.repository.UsuarioRepository;
import com.emanuelhonorio.service.dto.AnuncioDTO;
import com.emanuelhonorio.service.dto.UsuarioDTO;
import com.emanuelhonorio.service.mapper.AnuncioMapper;
import com.emanuelhonorio.service.mapper.UsuarioMapper;

@RestController
@RequestMapping("/me")
public class MeResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@GetMapping
	public UsuarioDTO getMe(Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		return UsuarioMapper.usuarioToUsuarioDTO(usuarioOpt.get());
	}
	
	@PutMapping("/{id}")
	public UsuarioDTO updateMe(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
		// TODO: Lógica de atualização
		return usuarioDTO;
	}
	
	@GetMapping("/anuncios")
	public List<AnuncioDTO> getMeusAnuncios(Principal principal) {
		List<Anuncio> meusAnuncios = anuncioRepository.findByUsuarioEmailIgnoreCase(principal.getName());
		return AnuncioMapper.anunciosToAnunciosDTO(meusAnuncios);
	}
	
	@GetMapping("/contatos")
	public List<Contato> getMeusContatos(Principal principal) {
		List<Contato> meusContatos = contatoRepository.findByUsuarioEmailIgnoreCase(principal.getName());
		return meusContatos;
	}
	
	@PostMapping("/contatos")
	public Contato addContato(@RequestBody @Valid Contato contato, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		contato.setId(null);
		contato.setUsuario(usuarioOwner);
		contato = contatoRepository.save(contato);
		return contato;
	}
}
