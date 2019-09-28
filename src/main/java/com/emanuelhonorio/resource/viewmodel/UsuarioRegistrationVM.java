package com.emanuelhonorio.resource.viewmodel;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.emanuelhonorio.service.dto.UsuarioDTO;

public class UsuarioRegistrationVM extends UsuarioDTO {

	@NotNull
	@Size(min = 3, max = 25)
	@Column(nullable = false, length = 300)
	private String senha;
	
	public UsuarioRegistrationVM() {
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
