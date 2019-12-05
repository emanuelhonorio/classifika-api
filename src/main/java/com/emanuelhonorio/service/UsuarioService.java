package com.emanuelhonorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emanuelhonorio.error.exceptions.EmailAlreadyUsedException;
import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.repository.UsuarioRepository;
import com.emanuelhonorio.service.dto.UsuarioDTO;

@Service
public class UsuarioService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario register(UsuarioDTO usuarioDTO) {
		
		if ( usuarioRepository.findByEmailIgnoreCase(usuarioDTO.getEmail()).isPresent() ) {
			throw new EmailAlreadyUsedException();
		}
		
		Usuario novoUsuario = new Usuario();
		String encryptedPassword = passwordEncoder.encode(usuarioDTO.getSenha());
		
		novoUsuario.setApelido(usuarioDTO.getApelido());
		novoUsuario.setEmail(usuarioDTO.getEmail());
		novoUsuario.setSenha(encryptedPassword);
		
		novoUsuario = usuarioRepository.save(novoUsuario);
		
		return novoUsuario;
	}
}
