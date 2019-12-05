package com.emanuelhonorio.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emanuelhonorio.error.exceptions.ResourceNotFoundException;
import com.emanuelhonorio.error.exceptions.ResourceOwnerException;
import com.emanuelhonorio.model.Anuncio;
import com.emanuelhonorio.model.Contato;
import com.emanuelhonorio.model.Foto;
import com.emanuelhonorio.model.QAnuncio;
import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.repository.AnuncioRepository;
import com.emanuelhonorio.repository.FotoRepository;
import com.emanuelhonorio.service.dto.AnuncioDTO;
import com.emanuelhonorio.service.filter.AnuncioFilter;
import com.emanuelhonorio.service.mapper.AnuncioMapper;
import com.querydsl.core.BooleanBuilder;

@Service
@Transactional
public class AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;
	
	@Autowired
	private FotoRepository fotoRepository;
	
	
	public Page<Anuncio> filtrar(AnuncioFilter anuncioFilter, int page, int size) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		if (!StringUtils.isEmpty(anuncioFilter.getTitulo())) {
			booleanBuilder.and(QAnuncio.anuncio.titulo.like("%"+anuncioFilter.getTitulo()+"%"));
		}
		
		if (anuncioFilter.getUf() != null) {
			booleanBuilder.and(QAnuncio.anuncio.uf.eq(anuncioFilter.getUf()));
		}
		
		if (anuncioFilter.getCategoria() != null) {
			booleanBuilder.and(QAnuncio.anuncio.categoria.id.eq(anuncioFilter.getCategoria()));
		}
		
		// SOMENTE ANÚNCIOS VÁLIDOS
		booleanBuilder.and(QAnuncio.anuncio.ativo.eq(true));
		booleanBuilder.and(QAnuncio.anuncio.dataExpiracao.gt(LocalDateTime.now()));
	
		return anuncioRepository.findAll(booleanBuilder.getValue(), PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));	
	}
	
	public Anuncio publicarAnuncio(AnuncioDTO anuncioDTO, Usuario usuarioOwner) {
		Anuncio anuncio = AnuncioMapper.anuncioDTOToAnuncio(anuncioDTO);
		verificarContatoPertenceAoUsuario(anuncio.getContato(), usuarioOwner);
		anuncio.setId(null);
		anuncio.setUsuario(usuarioOwner);
		anuncio.setAtivo(true);
		anuncio = anuncioRepository.save(anuncio);
		return anuncio;
	}

	public void excluirAnuncio(Long anuncioId, Usuario usuario) {
		Anuncio anuncio = buscarPeloId(anuncioId);
		
		if (!usuario.isAdmin()) {
			verificarAnuncioPertenceAoUsuario(anuncio, usuario);
		}
		
		anuncioRepository.deleteById(anuncioId);
	}

	public Anuncio buscarPeloId(Long id) {
		Optional<Anuncio> anuncioOpt = anuncioRepository.findById(id);
		if (!anuncioOpt.isPresent()) {
			throw new ResourceNotFoundException();
		}
		return anuncioOpt.get();
	}

	public void toggleAtivo(Long anuncioId, Usuario usuario) {
		Anuncio anuncio = buscarPeloId(anuncioId);
		
		if (!usuario.isAdmin()) {
			verificarAnuncioPertenceAoUsuario(anuncio, usuario);
		}
		
		anuncio.setAtivo(!anuncio.isAtivo());
		anuncioRepository.save(anuncio);
	}

	public Anuncio atualizarAnuncio(Long anuncioId, @Valid AnuncioDTO anuncioDTO, Usuario usuario) {
		Anuncio anuncio = buscarPeloId(anuncioId);
		
		if (!usuario.isAdmin()) {
			verificarAnuncioPertenceAoUsuario(anuncio, usuario);
			verificarContatoPertenceAoUsuario(anuncio.getContato(), usuario);
		}
		
		anuncioDTO.setAtivo(anuncio.isAtivo());
		anuncioDTO.setDataExpiracao(anuncio.getDataExpiracao());
		anuncioDTO.setAtualizadoEm(anuncio.getAtualizadoEm());
		anuncioDTO.setCriadoEm(anuncio.getCriadoEm());
		
		Anuncio anuncioAtualizado = AnuncioMapper.anuncioDTOToAnuncio(anuncioDTO);
		anuncioAtualizado.setId(anuncioId);
		
		anuncioAtualizado = anuncioRepository.save(anuncioAtualizado);
		return anuncioAtualizado;
	}
	

	public Anuncio uparFotos(Long anuncioId, Usuario usuario, MultipartFile[] fotos) throws IOException {
		Anuncio anuncio = buscarPeloId(anuncioId);
				
		if (!usuario.isAdmin()) {
			verificarAnuncioPertenceAoUsuario(anuncio, usuario);
		}		
		fotoRepository.deleteAllByAnuncioId(anuncio.getId());
		
		List<Foto> fotosSalvas = new ArrayList<>();
		
		for (MultipartFile foto : fotos) {
			fotosSalvas.add(new Foto(foto.getBytes(), anuncio));
		}
		
		fotosSalvas = fotoRepository.saveAll(fotosSalvas);

		anuncio.setFotos(fotosSalvas);		
		return anuncio;
	}
	
	public void excluirFoto(Long fotoId, Usuario usuarioOwner) {
		Foto foto = buscarFotoPeloId(fotoId);
		verificarFotoPertenceAoUsuario(foto, usuarioOwner);
		
		fotoRepository.deleteById(fotoId);
	}

	private Foto buscarFotoPeloId(Long fotoId) {
		Optional<Foto> fotoOpt = fotoRepository.findById(fotoId);
		if (!fotoOpt.isPresent()) {
			throw new ResourceNotFoundException();
		}
		return fotoOpt.get();
	}
	
	private void verificarContatoPertenceAoUsuario(Contato contato, Usuario usuarioOwner) {
		if(!usuarioOwner.getContatos().contains(contato)) {
			throw new ResourceOwnerException();
		}
	}

	private void verificarAnuncioPertenceAoUsuario(Anuncio anuncio, Usuario publicador) throws ResourceOwnerException {
		if (!publicador.equals(anuncio.getUsuario())) {
			throw new ResourceOwnerException();
		}
	}
	
	private void verificarFotoPertenceAoUsuario(Foto foto, Usuario publicador) throws ResourceOwnerException {		
		if (!publicador.equals(foto.getAnuncio().getUsuario())) {
			throw new ResourceOwnerException();
		}
	}

}
