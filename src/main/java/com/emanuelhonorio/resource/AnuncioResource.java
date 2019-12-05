package com.emanuelhonorio.resource;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.emanuelhonorio.model.Foto;
import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.repository.FotoRepository;
import com.emanuelhonorio.repository.UsuarioRepository;
import com.emanuelhonorio.service.AnuncioService;
import com.emanuelhonorio.service.dto.AnuncioDTO;
import com.emanuelhonorio.service.filter.AnuncioFilter;
import com.emanuelhonorio.service.mapper.AnuncioMapper;

@RestController
@RequestMapping("/anuncios")
public class AnuncioResource {

	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<AnuncioDTO> listarAnuncios(
			@RequestParam(name = "page", defaultValue = "0") int page,
		    @RequestParam(name = "size", defaultValue = "500") int size,
			AnuncioFilter anuncioFilter) {
		Page<Anuncio> anunciosPage = anuncioService.filtrar(anuncioFilter, page, size);
		
		Page<AnuncioDTO> anunciosDTOPage = anunciosPage.map(new Function<Anuncio, AnuncioDTO>() {
			@Override
			public AnuncioDTO apply(Anuncio source) {
				return AnuncioMapper.anuncioToAnuncioDTO(source);
			}
		});
		
		return anunciosDTOPage;
	}
	
	@GetMapping("/{id}")
	public AnuncioDTO listarAnuncios(@PathVariable Long id) {
		return AnuncioMapper.anuncioToAnuncioDTO(anuncioService.buscarPeloId(id));
	}
	
	@GetMapping("/img/{id}")
	public ResponseEntity<?> getFotoResource(@PathVariable Long id) {
		Optional<Foto> foto = fotoRepository.findById(id);
		if(!foto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(foto.get().getArquivo());
	}
	
	@PostMapping
	public AnuncioDTO publicarAnuncio(Principal principal, @RequestBody @Valid AnuncioDTO anuncioDTO) {		
		Usuario usuarioPrincipal = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		Anuncio anuncioSalvo = anuncioService.publicarAnuncio(anuncioDTO, usuarioPrincipal);
		return AnuncioMapper.anuncioToAnuncioDTO(anuncioSalvo);
	}
	
	@PostMapping("/{id}/fotos")
	public AnuncioDTO uparFotos(@PathVariable Long id, @RequestParam("fotos") MultipartFile[] fotos, Principal principal) throws IOException {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		Anuncio anuncioAtualizado = anuncioService.uparFotos(id, usuarioOwner, fotos);
		return AnuncioMapper.anuncioToAnuncioDTO(anuncioAtualizado);
	}
	
	@DeleteMapping("/fotos/{fotoId}")
	public void excluirFoto(@PathVariable Long fotoId, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		anuncioService.excluirFoto(fotoId, usuarioOwner);
	}
	
	@PutMapping("/{id}")
	public AnuncioDTO atualizarMeuAnuncio(@PathVariable Long id, @RequestBody @Valid AnuncioDTO anuncioDTO, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		Anuncio anuncioAtualizado = anuncioService.atualizarAnuncio(id, anuncioDTO, usuarioOwner);
		return AnuncioMapper.anuncioToAnuncioDTO(anuncioAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public void excluirMeuAnuncio(@PathVariable Long id, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		anuncioService.excluirAnuncio(id, usuarioOwner);
	}
	
	@PatchMapping("/{id}/ativo")
	public void toogleAtivo(@PathVariable Long id, Principal principal) {
		Usuario usuarioOwner = usuarioRepository.findByEmailIgnoreCase(principal.getName()).get();
		anuncioService.toggleAtivo(id, usuarioOwner);
	}

}
