package com.emanuelhonorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	List<Contato> findByUsuarioEmailIgnoreCase(String email);
}
