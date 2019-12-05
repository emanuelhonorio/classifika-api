package com.emanuelhonorio.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.service.UsuarioService;
import com.emanuelhonorio.service.dto.UsuarioDTO;
import com.emanuelhonorio.service.mapper.UsuarioMapper;

@RestController
public class AccountResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/register")
	public UsuarioDTO register(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioService.register(usuarioDTO);
		return UsuarioMapper.usuarioToUsuarioDTO(usuario);
	}
}
