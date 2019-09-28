package com.emanuelhonorio.service.filter;

import com.emanuelhonorio.model.enums.EstadoUF;

public class AnuncioFilter {
	private String titulo;
	private Long categoria;
	private EstadoUF uf;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public EstadoUF getUf() {
		return uf;
	}

	public void setUf(EstadoUF uf) {
		this.uf = uf;
	}

}