package com.emanuelhonorio.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.emanuelhonorio.model.Anuncio;
import com.emanuelhonorio.service.dto.AnuncioDTO;

public class AnuncioMapper {
	
	public static AnuncioDTO anuncioToAnuncioDTO(Anuncio anuncio) {
		AnuncioDTO anuncioDTO = new AnuncioDTO();
		if (anuncio != null) {
			BeanUtils.copyProperties(anuncio, anuncioDTO, "usuario");
			
			if(anuncio.getUsuario() != null) {
				anuncioDTO.setUsuario(UsuarioMapper.usuarioToUsuarioDTO(anuncio.getUsuario()));
			}	
		}
		return anuncioDTO;
	}
	
	public static List<AnuncioDTO> anunciosToAnunciosDTO(List<Anuncio> anuncios) {
		List<AnuncioDTO> anuncioDTOList = new ArrayList<>();
		
		for(Anuncio anuncio : anuncios) {
			anuncioDTOList.add(anuncioToAnuncioDTO(anuncio));
		}
		return anuncioDTOList;
	}
	
	
	public static Anuncio anuncioDTOToAnuncio(AnuncioDTO anuncioDTO) {
		Anuncio anuncio = new Anuncio();
		if (anuncioDTO != null) {
			BeanUtils.copyProperties(anuncioDTO, anuncio, "usuario");
			
			if (anuncioDTO.getUsuario() != null) {
				anuncio.setUsuario(UsuarioMapper.usuarioDTOToUsuario(anuncioDTO.getUsuario()));
			}	
		}
		return anuncio;
	}
}
