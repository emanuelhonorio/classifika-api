package com.emanuelhonorio.resource;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.model.Anuncio;
import com.emanuelhonorio.model.Foto;
import com.emanuelhonorio.repository.FotoRepository;
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

}
