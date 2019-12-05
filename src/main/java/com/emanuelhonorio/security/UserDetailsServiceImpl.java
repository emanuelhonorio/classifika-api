package com.emanuelhonorio.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emanuelhonorio.model.Role;
import com.emanuelhonorio.model.Usuario;
import com.emanuelhonorio.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(email);
		
		if (!usuarioOpt.isPresent()) {
			throw new UsernameNotFoundException("email inválido");
		}
		
		return new User(usuarioOpt.get().getEmail(), usuarioOpt.get().getSenha(), getAuthorities(usuarioOpt.get()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		for (Role permissao : usuario.getPermissoes()) {
			authorities.add(new SimpleGrantedAuthority(permissao.getNome()));
		}
		
		return authorities;
	}

}
