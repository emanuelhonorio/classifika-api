package com.emanuelhonorio.service.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.emanuelhonorio.model.Categoria;
import com.emanuelhonorio.model.Contato;
import com.emanuelhonorio.model.Foto;
import com.emanuelhonorio.model.enums.EstadoUF;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class AnuncioDTO {

	private Long id;

	@NotBlank
	private String titulo;

	private String descricao;

	private Double preco;

	@NotNull
	private Categoria categoria;

	@NotNull
	private Contato contato;

	private UsuarioDTO usuario;

	private List<Foto> fotos;

	@NotBlank
	private String cep;

	@NotNull
	private EstadoUF uf;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime criadoEm;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime atualizadoEm;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataExpiracao;

	@JsonProperty(access = Access.READ_ONLY)
	private boolean ativo;

	@JsonProperty(access = Access.READ_ONLY)
	private boolean expirado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public EstadoUF getUf() {
		return uf;
	}

	public void setUf(EstadoUF uf) {
		this.uf = uf;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(LocalDateTime atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isExpirado() {
		return expirado;
	}

	public void setExpirado(boolean expirado) {
		this.expirado = expirado;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
