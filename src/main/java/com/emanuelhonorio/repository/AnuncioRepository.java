package com.emanuelhonorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.emanuelhonorio.model.Anuncio;
import com.emanuelhonorio.model.enums.EstadoUF;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>,
	QuerydslPredicateExecutor<Anuncio> {
	
	List<Anuncio> findByUsuarioEmailIgnoreCase(String email);
	List<Anuncio> findAllByCategoriaIdAndUf(Long id, EstadoUF uf);
	List<Anuncio> findAllByCategoriaId(Long id);
	List<Anuncio> findAllByUf(EstadoUF uf);
}
