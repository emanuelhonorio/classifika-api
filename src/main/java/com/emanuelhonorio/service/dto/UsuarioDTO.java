package com.emanuelhonorio.service.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.emanuelhonorio.model.Contato;
import com.emanuelhonorio.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UsuarioDTO {

	private Long id;

	@Size(max = 200)
	private String nomeCompleto;

	@NotBlank
	@Size(max = 80)
	private String apelido;

	@NotBlank
	@Email
	@Size(max = 300)
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull
	@Size(min = 3, max = 25)
	private String senha;

	private Set<Contato> contatos;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime criadoEm;

	@JsonProperty(access = Access.READ_ONLY)
	private Set<Role> permissoes = new HashSet<>();

	public UsuarioDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Set<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Role> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Role> permissoes) {
		this.permissoes = permissoes;
	}

}
