package com.emanuelhonorio.service.mapper;

import org.springframework.beans.BeanUtils;

import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.service.dto.UsuarioDTO;

public class UsuarioMapper {
	
	public static UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		if (usuario != null) {
			BeanUtils.copyProperties(usuario, usuarioDTO);
		}
		return usuarioDTO;
	}
	
	public static Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		if (usuarioDTO != null) {
			BeanUtils.copyProperties(usuarioDTO, usuario);
		}
		return usuario;
	}
	
}
