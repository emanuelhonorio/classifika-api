package com.emanuelhonorio.resource;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emanuelhonorio.model.Anuncio;
import com.emanuelhonorio.model.Contato;
import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.repository.AnuncioRepository;
import com.emanuelhonorio.repository.ContatoRepository;
import com.emanuelhonorio.repository.UsuarioRepository;
import com.emanuelhonorio.service.AnuncioService;
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
	
	@Autowired
	private AnuncioService anuncioService;
	
	@GetMapping
	public UsuarioDTO getMe(Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		return UsuarioMapper.usuarioToUsuarioDTO(usuarioOpt.get());
	}
	
	@PutMapping
	public UsuarioDTO updateMe(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		// TODO: Lógica de atualização
		return usuarioDTO;
	}
	
	@GetMapping("/anuncios")
	public List<AnuncioDTO> getMeusAnuncios(Principal principal) {
		List<Anuncio> meusAnuncios = anuncioRepository.findByUsuarioEmailIgnoreCase(principal.getName());
		return AnuncioMapper.anunciosToAnunciosDTO(meusAnuncios);
	}
	
	@PostMapping("/anuncios")
	public AnuncioDTO publicarAnuncio(Principal principal, @RequestBody @Valid AnuncioDTO anuncioDTO) {		
		Usuario usuarioPrincipal = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		Anuncio anuncioSalvo = anuncioService.publicarAnuncio(anuncioDTO, usuarioPrincipal);
		return AnuncioMapper.anuncioToAnuncioDTO(anuncioSalvo);
	}
	
	@PostMapping("/anuncios/{id}/fotos")
	public AnuncioDTO uparFotos(@PathVariable Long id, @RequestParam("fotos") MultipartFile[] fotos, Principal principal) throws IOException {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		Anuncio anuncioAtualizado = anuncioService.uparFotos(id, usuarioOwner, fotos);
		return AnuncioMapper.anuncioToAnuncioDTO(anuncioAtualizado);
	}
	
	@DeleteMapping("/anuncios/fotos/{fotoId}")
	public void excluirFoto(@PathVariable Long fotoId, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		anuncioService.excluirFoto(fotoId, usuarioOwner);
	}
	
	@PutMapping("/anuncios/{id}")
	public AnuncioDTO atualizarMeuAnuncio(@PathVariable Long id, @RequestBody @Valid AnuncioDTO anuncioDTO, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		Anuncio anuncioAtualizado = anuncioService.atualizarAnuncio(id, anuncioDTO, usuarioOwner);
		return AnuncioMapper.anuncioToAnuncioDTO(anuncioAtualizado);
	}
	
	@DeleteMapping("/anuncios/{id}")
	public void excluirMeuAnuncio(@PathVariable Long id, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		anuncioService.excluirAnuncio(id, usuarioOwner);
	}
	
	@PatchMapping("/anuncios/{id}/ativo")
	public void toogleAtivo(@PathVariable Long id, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		anuncioService.toggleAtivo(id, usuarioOwner);
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
